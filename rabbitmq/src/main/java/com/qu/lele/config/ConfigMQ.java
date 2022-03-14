package com.qu.lele.config;

import com.qu.lele.constant.ConstantMQ;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 09-27
 * 交换机4种模式(交换机默认是持久化的)
 * 1.fanout-->发布订阅
 * 2.direct-->指定路由
 * 3.topic--->主题模式
 */
@Configuration
public class ConfigMQ {
    /**
     * fanout模式-->发布订阅
     * 只要是和交换机绑定队列都会收到消息
     * @return
     */
    @Bean("queueA")
    public Queue queueA() {
        return QueueBuilder.durable(ConstantMQ.QUEUE_A).build();
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(ConstantMQ.EXCHANGE_A).build();
    }

    @Bean
    public Binding bindingBuilderA(@Qualifier("queueA") Queue queueA,
                                   @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    /**
     * direct模式--->指定路由key
     */
    @Bean("queueB")
    public Queue queueB() {
        return QueueBuilder.durable(ConstantMQ.QUEUE_B).build();
    }
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(ConstantMQ.QUEUE_D).build();
    }

    @Bean("directExchange")
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(ConstantMQ.EXCHANGE_B).build();
    }

    @Bean
    public Binding bindingBuilderB(@Qualifier("queueB") Queue queueB,
                                   @Qualifier("directExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queueB).to(directExchange).with(ConstantMQ.ROUTING_B);
    }
    @Bean
    public Binding bindingBuilderC(@Qualifier("queueD") Queue queueD,
                                   @Qualifier("directExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queueD).to(directExchange).with(ConstantMQ.ROUTING_A);
    }

    /**
     * topic模式--->主题
     * 格式为:topic.test
     * (*.topic.*) *代表模糊匹配1个
     * (#.topic.#) #代表模糊匹配0个或者多个
     */
    @Bean("queueC")
    public Queue queueC() {
        return QueueBuilder
                .durable(ConstantMQ.QUEUE_C)
                .deadLetterExchange(ConstantMQ.EXCHANGE_DEAD_LETTER)
                .deadLetterRoutingKey(ConstantMQ.ROUTING_LETTER)
                .build();
    }

    @Bean("queueE")
    public Queue queueE() {
        return QueueBuilder
                .durable(ConstantMQ.QUEUE_E)
                .deadLetterExchange(ConstantMQ.EXCHANGE_DEAD_LETTER)
                .deadLetterRoutingKey(ConstantMQ.ROUTING_LETTER)
                .build();
    }

    @Bean("topicExchange")
    public TopicExchange topicExchange() {
        return ExchangeBuilder.topicExchange(ConstantMQ.EXCHANGE_C).build();
    }

    @Bean
    public Binding bindingBuilderD(@Qualifier("queueC") Queue queueC,
                                   @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queueC).to(topicExchange).with(ConstantMQ.ROUTING_C);
    }

    @Bean
    public Binding bindingBuilderE(@Qualifier("queueE") Queue queueE,
                                   @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queueE).to(topicExchange).with(ConstantMQ.ROUTING_D);
    }

    /**
     * 错误队列
     * @return
     */
    @Bean("errorQueue")
    public Queue errorQueue(){
        return QueueBuilder.durable(ConstantMQ.QUEUE_ERROR).build();
    }

    @Bean("errorMessageExchange")
    public DirectExchange errorMessageExchange(){
        return ExchangeBuilder.directExchange(ConstantMQ.EXCHANGE_ERROR).build();
    }

    @Bean
    public Binding errorBinding(@Qualifier("errorQueue") Queue errorQueue,
                                @Qualifier("errorMessageExchange") DirectExchange errorMessageExchange){
        return BindingBuilder.bind(errorQueue).to(errorMessageExchange).with(ConstantMQ.ROUTING_ERROR);
    }

    /**
     * 死信队列
     */
    @Bean("queueDeadLetter")
    public Queue queueDeadLetter() {
        return QueueBuilder.durable(ConstantMQ.QUEUE_DEAD_LETTER).build();
    }

    @Bean("topicExchangeLetter")
    public TopicExchange topicExchangeLetter() {
        return ExchangeBuilder.topicExchange(ConstantMQ.EXCHANGE_DEAD_LETTER).build();
    }

    @Bean
    public Binding bindingBuilderF(@Qualifier("queueDeadLetter") Queue queueDeadLetter,
                                   @Qualifier("topicExchangeLetter") TopicExchange topicExchangeLetter) {
        return BindingBuilder.bind(queueDeadLetter).to(topicExchangeLetter).with(ConstantMQ.ROUTING_LETTER);
    }

}
