package com.qu.lele.consume;

import com.alibaba.fastjson.JSONObject;
import com.qu.lele.callback.MsgCallBack;
import com.qu.lele.callback.MsgReturnBack;
import com.qu.lele.constant.ConstantMQ;
import com.qu.lele.dao.MsgLogDao;
import com.qu.lele.dto.MsgLog;
import com.qu.lele.dto.MsgStatus;
import com.qu.lele.util.Times;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 15-14
 */
@Component
@Slf4j
public class ConsumeMQ {
    @Autowired
    private Environment en;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgCallBack msgCallBack;
    @Autowired
    private MsgReturnBack msgReturnBack;
    @Resource
    private MsgLogDao msgLogDao;
    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
    private String maxAttempts;
    /**
     * 业务正常消费
     * @param messageDto
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_A)
    public void receiveMsgA(@Payload String messageDto, Message msg, Channel channel) {
        try {
            log.info("messageDtoA-->{}",messageDto);
            int a = 10/0;
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务异常消费
     * 一旦try catch异常后，rabbitmq自身重试机制就会失效，所以需要我们自己实现重试
     * @param messageDto
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_B)
    public void receiveMsgB(@Payload String messageDto, Message msg, Channel channel) {
        try {
            log.info("messageDtoB-->{}",messageDto);
            int a = 10/0;
            /**
             * 消费者手动ack
             * 参数描述
             * deliveryTag:  消费tag
             * multiple:     false表示只对当前消息ack,true表示批量ack(tag<=当前DeliveryTag的值都会被消费)
             */
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {

        }
    }

    /**
     * 业务异常消费
     * try catch + 重试 + 手动ack + 死信队列
     * 重试次数超过限制，极有可能当前业务存在问题，可以将消息插入数据库+人工补偿机制处理(这样就可以避免消费丢失了)
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_C)
    public void receiveMsgC(Message msg, Channel channel) {
        //模拟业务出现异常
        Object parse = null;
        try {
            String json = new String(msg.getBody(), "utf-8");
            parse = JSONObject.parse(json);
            log.info("messageDtoC-->{}",parse);
            int a = 10/0;
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            //至于这里重试几次可以根据具体业务来做调整
            try {
                Integer maxRetryCount = Integer.parseInt(maxAttempts);
                Map<String, Object> headers = msg.getMessageProperties().getHeaders();
                Integer currentRetryCount = 0;
                if (headers.containsKey("retry-count")) {
                    currentRetryCount = (Integer) headers.get("retry-count");
                }
                currentRetryCount++;
                if (currentRetryCount <= maxRetryCount) {
                    log.info("currentRetryCount:{},messageId:{}",currentRetryCount,msg.getMessageProperties().getMessageId());
                    //第二个参数multiple表示是否批量ack, false表示只确认当前的tag,true表示会确认<=当前tag消息
                    channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
                    CorrelationData correlationData = new CorrelationData(msg.getMessageProperties().getMessageId());
                    Integer finalCurrentRetryCount = currentRetryCount;
                    //重新发送消息需要注意的是：如果当前业务需要保证消费者顺序消费，那这种发送消息会有问题
                    rabbitTemplate.setConfirmCallback(msgCallBack);
                    rabbitTemplate.setReturnsCallback(msgReturnBack);
                    rabbitTemplate.convertAndSend(msg.getMessageProperties().getReceivedExchange(),
                            msg.getMessageProperties().getReceivedRoutingKey(), parse,
                            message -> {
                                message.getMessageProperties().setRedelivered(true);
                                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                                message.getMessageProperties().setMessageId(msg.getMessageProperties().getMessageId());
                                message.getMessageProperties().setHeader("retry-count", finalCurrentRetryCount);
                              return message;
                            },
                            correlationData);
                } else {
                    //第二个参数requeue表示是否重新入队, false不重新入队，true重新入队
                    channel.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
                    //channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false,false);
                }
            } catch (IOException ex) {
                log.error("重试出现异常********");
            }
        }
    }

    /**
     * 业务异常消费
     * try catch  + 手动ack + 定时任务
     * @param msg
     * @param channel
     * 事务消息
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_E)
    @Transactional
    public void receiveE(Message msg, Channel channel) {
        //模拟业务出现异常
        try {
            String json = new String(msg.getBody(), "utf-8");
            Object parse = JSONObject.parse(json);
            log.info("messageDtoC-->{}",parse);
            String messageId = msg.getMessageProperties().getMessageId();
            Integer status = msgLogDao.selectByPrimaryKey(messageId).getStatus();
            //这个消息已经被消费过了,直接丢弃
            if (status == MsgStatus.Consumption.getStatus()) {
                channel.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
            } else {
                //否则更新消息日志状态，设置为已消费
                MsgLog msgLog = new MsgLog();
                msgLog.setId(messageId);
                msgLog.setStatus(MsgStatus.Consumption.getStatus());
                msgLog.setUpdatetime(Times.getTime());
                //这2行代码必须要同时成功
                msgLogDao.updateByPrimaryKeySelective(msgLog);
                channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
            }
        } catch (Exception e) {
            try {
                //如果之前没有重新投递，再次入队
                if (!msg.getMessageProperties().getRedelivered()) {
                    log.info("消息重新入队,ID:{}",msg.getMessageProperties().getMessageId());
                    channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false,true);
                } else {
                    //直接丢弃消息,通过定时任务来发送
                    channel.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
                }
            } catch (Exception ex) {
               log.error("消息ack异常");
            }

        }
    }

    /**
     * 死信队列(当队列满了、消息TTL过期、basicNack()/basicReject(),且requeue=false)
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_DEAD_LETTER)
    public void receiveMsgD(Message msg, Channel channel) {
        try {
            log.info("死信队列receiveMsgD-->{}",new String(msg.getBody(),"utf-8"));
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 错误消息队列
     * @param msg
     * @param channel
     */
    @RabbitListener(queues = ConstantMQ.QUEUE_ERROR)
    public void receiveMsgE(Message msg, Channel channel) {
        try {
            log.info("错误消息队列receiveMsgE-->{}",new String(msg.getBody(),"utf-8"));
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
