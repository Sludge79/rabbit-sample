package com.sludge.rabbitmqsample.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sludge.rabbitmqsample.model.Customer;
import com.sludge.rabbitmqsample.model.CustomerMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class Api {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/page")
    public Object page(){
        return customerMapper.selectPage(new Page<>(0, 10), new QueryWrapper<Customer>());
    }

    @GetMapping("/sendMsg2Que")
    public void sendMsg2Que(String customerId){
        rabbitTemplate.convertAndSend("haha",customerId);
    }
}
