package com.qu.lele.constant;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 09-28
 */
public class ConstantMQ {

    public static final String QUEUE_A = "queueA";
    public static final String EXCHANGE_A = "exchangeA";
    public static final String ROUTING_A = "A";

    public static final String QUEUE_B = "queueB";
    public static final String QUEUE_D = "queueD";
    public static final String EXCHANGE_B = "exchangeB";
    public static final String ROUTING_B = "B";

    public static final String QUEUE_C = "queueC";
    public static final String QUEUE_E = "queueE";
    public static final String EXCHANGE_C = "exchangeC";
    public static final String ROUTING_C = "*.topic";
    public static final String ROUTING_D = "topic.#";

    public static final String QUEUE_DEAD_LETTER = "deadLetterQueue";
    public static final String EXCHANGE_DEAD_LETTER= "exchangeDeadLetter";
    public static final String ROUTING_LETTER = "deadLetter.topic";

    public static final String EXCHANGE_ERROR = "error.direct";
    public static final String QUEUE_ERROR = "queueError";
    public static final String ROUTING_ERROR = "error";
}
