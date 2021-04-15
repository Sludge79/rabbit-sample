package com.sludge.rabbitmqsample.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sludge.rabbitmqsample.model.Customer;
import com.sludge.rabbitmqsample.model.CustomerMapper;
import com.sludge.rabbitmqsample.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
@Slf4j
public class ConsumerSludge {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "haha")
    public void haha(Message message) {
        String s = new String(message.getBody(), StandardCharsets.UTF_8);
        Page<Customer> customerPage = customerMapper.selectPage(new Page<>(0, 10), new QueryWrapper<Customer>());
        log.info("请求列表信息 {}", customerPage);
        HashMap<String, Page<Customer>> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(s, customerPage);
        rabbitTemplate.convertAndSend("replyHaha", JSONUtil.toJSONString(objectObjectHashMap));
    }

    @RabbitListener(queues = "replyHaha")
    public void replyHaha(Message message) {
        String s = new String(message.getBody(), StandardCharsets.UTF_8);
        HashMap hashMap = JSONUtil.toJavaBean(s, HashMap.class);
        log.info("已查阅到消息{}", hashMap);
    }
}
