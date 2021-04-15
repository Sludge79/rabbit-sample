package com.sludge.rabbitmqsample.rabbitconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplyHahaBinding {



    @Bean
    public Queue replyHaha(){
        return new Queue("replyHaha");
    }

    @Bean
    public DirectExchange replyHahaExchange() {
        // 参数1：队列
        // 参数2：是否持久化
        // 参数3：是否自动删除
        return new DirectExchange("reply_haha_exchange", true, true);
    }

    @Bean
    public Binding replyBind() {
        return BindingBuilder.bind(replyHaha()).to(replyHahaExchange()).with("replyHaha");
    }
}
