package com.qu.lele.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: 屈光乐
 * @create: 2022-03-13 11-02
 * 客户端发送消息到交换机，并没有对应到路由，此时给客户端返回一个应答(需要与mandatory配置一起使用)
 */
@Component
@Slf4j
public class MsgReturnBack implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("replyCode:{},replyText:{},exchange:{},routingKey:{},message:{}",
                returnedMessage.getReplyCode(),returnedMessage.getReplyText(),returnedMessage.getExchange(),
                returnedMessage.getRoutingKey(),returnedMessage.getMessage());
    }
}
