package com.qu.lele.service;

import com.alibaba.fastjson.JSONObject;
import com.qu.lele.constant.ConstantMQ;
import com.qu.lele.dao.MsgLogDao;
import com.qu.lele.dto.MessageDto;
import com.qu.lele.dto.MsgLog;
import com.qu.lele.dto.MsgStatus;
import com.qu.lele.util.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 10-12
 */
@Service
@Slf4j
public class TestService {
    @Autowired
    private RabbitTemplate template;
    @Resource
    private MsgLogDao msgLogDao;
    /**
     * 开启异步确认机制
     * @param rand
     * @return
     */
    public String asyncConfirm(String rand){
        String msg = "message_"+ rand;
        //添加ReturnCallBack回调 (当路由到队列失败时发生回调需要与mandatory参数搭配使用)
        template.setReturnsCallback(returnedMessage -> log.info("replyCode:{},replyText:{},exchange:{},routingKey:{},message:{}",
                returnedMessage.getReplyCode(),returnedMessage.getReplyText(),returnedMessage.getExchange(),
                returnedMessage.getRoutingKey(),returnedMessage.getMessage()));
        //方式1：添加confirmCallBack回调
        //template.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> log.debug("messageId:{},ack:{},cause:{}",correlationData.getId(),ack,cause));
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replaceAll("-",""));
        //方式2：添加confirmCallBack回调
        correlationData.getFuture().addCallback(confirm -> {
                    if (confirm.isAck()) {
                        //正常ack情况 把消息发送到了交换机
                        log.info("消息发送到了交换机，ID:{}",correlationData.getId());
                    } else {
                        //nack情况  消息没有发送到交换机
                        log.info("消息没有发送到交换机: {},ID:{}",confirm.getReason(),correlationData.getId());
                    }
                },
                //连接MQ异常
                throwable -> log.error("消息发送失败:连接MQ异常"));
        //发送消息
        MessageDto messageDto = new MessageDto();
        messageDto.setMsg(msg);
        // template.convertAndSend(ConstantMQ.EXCHANGE_B,ConstantMQ.ROUTING_B,messageDto,correlationData);
        template.convertAndSend(ConstantMQ.EXCHANGE_C,"CC.topic",JSONObject.toJSONString(messageDto),
                message -> {
                    //消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setMessageId(UUID.randomUUID().toString().replaceAll("-",""));
                    return message;
                },
                correlationData);
        //template.convertAndSend(ConstantMQ.EXCHANGE_C,"topic.AA.CC",JSONObject.toJSONString(messageDto),correlationData);
        //template.convertAndSend(ConstantMQ.EXCHANGE_A,ConstantMQ.QUEUE_A,JSONObject.toJSONString(messageDto),correlationData);
        return "success";
    }

    /**
     * @param rand
     * @return
     */
    public String test(String rand) {
        String msg = "message_"+ rand;
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uuid);
        //发送消息
        MessageDto messageDto = new MessageDto();
        messageDto.setMsg(msg);
        String json = JSONObject.toJSONString(messageDto);
        // template.convertAndSend(ConstantMQ.EXCHANGE_B,ConstantMQ.ROUTING_B,JSONObject.toJSONString(messageDto),correlationData);
        template.convertAndSend(ConstantMQ.EXCHANGE_C,"CC.topic", json,
                message -> {
                    //消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setMessageId(uuid);
                    return message;
                },
                correlationData);
        //template.convertAndSend(ConstantMQ.EXCHANGE_C,"topic.AA.CC",JSONObject.toJSONString(messageDto),correlationData);
        //template.convertAndSend(ConstantMQ.EXCHANGE_A,ConstantMQ.QUEUE_A,JSONObject.toJSONString(messageDto),correlationData);
        return "success";
    }

    public String testMsg(String rand) {
        String msg = "message_"+ rand;
        String msgId = UUID.randomUUID().toString().replaceAll("-","");
        MessageDto messageDto = new MessageDto();
        messageDto.setMsg(msg);
        String json = JSONObject.toJSONString(messageDto);
        //先插入日志消息
        MsgLog msgLog = new MsgLog();
        msgLog.setId(msgId);
        msgLog.setMsg(json);
        msgLog.setExchange(ConstantMQ.EXCHANGE_C);
        msgLog.setRoutingkey("CC.topic");
        msgLog.setStatus(MsgStatus.Deliverying.getStatus());
        msgLog.setTrycount(0);
        msgLog.setCreatetime(Times.getTime());
        msgLog.setAlertid("TestUser");
        msgLogDao.insertSelective(msgLog);
        //发送消息
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(msgId);
        template.convertAndSend(ConstantMQ.EXCHANGE_C,"topic.CC", json,
                message -> {
                    //消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setMessageId(msgId);
                    return message;
                },
                correlationData);
        return "success";
    }

}
