package com.las.lasbackenduser3000.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq配置类
 * @author martin
 */
@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_BOT_VERIFICATION = "queue_bot_verification";
    public static final String EXCHANGE_BOT_VERIFICATION="exchange_bot_verification";
    public static final String ROUTING_KEY_REPORT="bot.#.verification.#";


    /**
     * 声明exchange_verification交换机
     * @return 交换机
     */
    @Bean(EXCHANGE_BOT_VERIFICATION)
    public Exchange exchangeVerification(){
        //持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_BOT_VERIFICATION).durable(true).build();
    }

    /**
     * 声明queue_verification_registration队列
     * @return 队列
     */
    @Bean(QUEUE_BOT_VERIFICATION)
    public Queue queueVerificationRegistration(){
        return new Queue(QUEUE_BOT_VERIFICATION);
    }





    /**
     * queue_verification_registration绑定交换机，指定routingKey
     * @param queue 队列
     * @param exchange 交换机
     * @return 绑定
     */
    @Bean
    public Binding bindingQueueVerificationRegistration(@Qualifier(QUEUE_BOT_VERIFICATION) Queue queue,
                                           @Qualifier(EXCHANGE_BOT_VERIFICATION) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_REPORT).noargs();
    }



}
