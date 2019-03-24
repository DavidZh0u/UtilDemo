package com.o98k.handler;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender1 {

    private String topic = "hello";

    private static int flag = 1;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1-NO: "+ flag++ + new Date();
        System.out.println("Sender1 =>" + sendMsg);
        this.rabbitTemplate.convertAndSend(topic, sendMsg);
    }

    public void send(String msg){
        this.rabbitTemplate.convertAndSend(topic,"[data]=>"+msg);
    }
}