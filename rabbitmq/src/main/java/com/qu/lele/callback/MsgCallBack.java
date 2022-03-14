package com.qu.lele.callback;

import com.qu.lele.dao.MsgLogDao;
import com.qu.lele.dto.MsgLog;
import com.qu.lele.dto.MsgStatus;
import com.qu.lele.util.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: 屈光乐
 * @create: 2022-03-13 11-01
 * 生产者消息确认回调: 消息是否成功发送到交换机上
 */
@Component
@Slf4j
public class MsgCallBack implements RabbitTemplate.ConfirmCallback {
    @Resource
    private MsgLogDao msgLogDao;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
         if (ack) {
             //正常ack情况 把消息发送到了交换机
             log.info("消息已经成功发送到交换机,ID:{}",correlationData.getId());
             //投递成功更新消息状态
             MsgLog msgLog = new MsgLog();
             msgLog.setId(correlationData.getId());
             msgLog.setStatus(MsgStatus.DeliverySuccess.getStatus());
             msgLog.setUpdatetime(Times.getTime());
             msgLogDao.updateByPrimaryKeySelective(msgLog);
         } else {
             //nack情况  消息没有发送到交换机
             log.info("消息没有发送到交换机: {},ID:{}",cause,correlationData.getId());
         }
    }
}
