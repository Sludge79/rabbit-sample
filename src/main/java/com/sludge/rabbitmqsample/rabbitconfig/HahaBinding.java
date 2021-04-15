package com.sludge.rabbitmqsample.rabbitconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HahaBinding {



    @Bean
    public Queue haha(){
        return new Queue("haha");
    }

    @Bean
    public DirectExchange hahaExchange() {
        // 参数1：队列
        // 参数2：是否持久化
        // 参数3：是否自动删除
        return new DirectExchange("haha_exchange", true, true);
    }

    @Bean
    public Binding hahaBind() {
        return BindingBuilder.bind(haha()).to(hahaExchange()).with("haha");
    }
}
