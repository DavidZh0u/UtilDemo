package com.o98k.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String hello) throws InterruptedException{
        Thread.sleep(2000);
        System.out.println("Receiver1  : " + hello);
        int i = 5/0;

    }

}