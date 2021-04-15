package com.sludge.rabbitmqsample;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sludge.rabbitmqsample.model.Customer;
import com.sludge.rabbitmqsample.model.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSampleApplicationTests {

    @Autowired
    CustomerMapper customerMapper;


    @Test
    void contextLoads() {
		Page<Customer> customerPage = customerMapper.selectPage(new Page<>(0, 10), new QueryWrapper<Customer>());

		System.out.println(customerPage);
	}

}
