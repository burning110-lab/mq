package com.qu.lele.config;
import com.qu.lele.callback.MsgCallBack;
import com.qu.lele.callback.MsgReturnBack;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 屈光乐
 * @create: 2022-03-13 11-37
 * RabbitTemplate配置
 */
@Configuration
public class RabbitTemplateConfig {
    @Autowired
    private MsgCallBack msgCallBack;
    @Autowired
    private MsgReturnBack msgReturnBack;


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(msgCallBack);
        rabbitTemplate.setReturnsCallback(msgReturnBack);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
