package com.sludge.rabbitmqsample.test;

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
            Task task = new Task(restTemplate, i);
            new Thread(task).start();
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

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Object forObject = this.restTemplate.getForObject("http://localhost:8080/customer/page", Object.class);
            Object forObject = this.restTemplate.getForObject("http://localhost:8080/customer/sendMsg2Que?customerId=" + this.num, Object.class);
            System.out.println(this.num + "获取到了" + forObject);
        }
    }
}
