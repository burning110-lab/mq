package com.qu.lele;

import com.qu.lele.constant.ConstantMQ;
import com.qu.lele.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
class RabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFanoutExchange() {
        String message = "testFanoutExchange";
        rabbitTemplate.convertAndSend(ConstantMQ.EXCHANGE_A,"",new MessageDto(message));
    }

    @Test
    public void testDirectExchange() {
        String message = "testDirectExchange";
        rabbitTemplate.convertAndSend(ConstantMQ.EXCHANGE_B,ConstantMQ.ROUTING_B,new MessageDto(message));
    }

    @Test
    public void testTopicExchange() {
        String message = "testTopicExchange";
        rabbitTemplate.convertAndSend(ConstantMQ.EXCHANGE_C,"A.topic.C",new MessageDto(message));
        rabbitTemplate.convertAndSend(ConstantMQ.EXCHANGE_C,"AA.topic.BB",new MessageDto(message));
    }

}
