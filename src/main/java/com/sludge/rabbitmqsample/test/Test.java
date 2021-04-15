package com.sludge.rabbitmqsample.test;

import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

public class Test {

    static CountDownLatch countDownLatch = new CountDownLatch(2000);

    public static void main(String[] args) {
        doingQuery();
    }

    static void doingQuery() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 2000; i++) {
            new Thread(new Task(restTemplate, i)).start();
            countDownLatch.countDown();
        }
    }


    static class Task implements Runnable {

        int num;
        RestTemplate restTemplate;

        public Task(RestTemplate restTemplate, int num) {
            this.restTemplate = restTemplate;
            this.num = num;
        }

        @SneakyThrows
        @Override
        public void run() {
            countDownLatch.await();
            //直接调用
            Object forObject = this.restTemplate.getForObject("http://localhost:8080/customer/page", Object.class);
            //走队列
//            Object forObject = this.restTemplate.getForObject("http://localhost:8080/customer/sendMsg2Que?customerId=" + this.num, Object.class);
            System.out.println(this.num + "获取到了" + forObject);
        }
    }
}
