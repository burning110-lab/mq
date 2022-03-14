package com.qu.lele.task;

import com.qu.lele.callback.MsgCallBack;
import com.qu.lele.callback.MsgReturnBack;
import com.qu.lele.dao.MsgLogDao;
import com.qu.lele.dto.MsgLog;
import com.qu.lele.dto.MsgLogExample;
import com.qu.lele.dto.MsgStatus;
import com.qu.lele.util.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 屈光乐
 * @create: 2022-03-14 09-16
 * 定时任务处理失败消息
 */
@Component
@Slf4j
public class RedeliveryMsgTask {
    @Resource
    private MsgLogDao msgLogDao;
    @Autowired
    private MsgCallBack msgCallBack;
    @Autowired
    private MsgReturnBack msgReturnBack;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
    private String maxAttempts;

    @Scheduled(cron = "0/30 * * * * ? ")
    public void task() {
        MsgLogExample msgLogExample = new MsgLogExample();
        msgLogExample.createCriteria()
                .andStatusNotIn(Arrays.asList(MsgStatus.Consumption.getStatus()));
        List<MsgLog> msgLogs = msgLogDao.selectByExample(msgLogExample);
        msgLogs.forEach(item -> {
            Integer tryCount = item.getTrycount();
            Integer maxRetryCount = Integer.parseInt(maxAttempts);
            if (tryCount <= maxRetryCount) {
                rabbitTemplate.setConfirmCallback(msgCallBack);
                rabbitTemplate.setReturnsCallback(msgReturnBack);
                rabbitTemplate.convertAndSend(item.getExchange(),item.getRoutingkey(),item.getMsg(),
                        message-> {
                           message.getMessageProperties().setRedelivered(true);
                         message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                         message.getMessageProperties().setMessageId(item.getId());
                         return message;
                        },new CorrelationData(item.getId()));
                tryCount++;

                item.setTrycount(tryCount);
                item.setUpdatetime(Times.getTime());
                msgLogDao.updateByPrimaryKeySelective(item);
            } else {
               log.warn("msg: {},重发次数达到上线，请人工处理",item.getId());
            }
        });
    }
}
