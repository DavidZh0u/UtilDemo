package com.o98k.controller;

import com.o98k.handler.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }

    @PostMapping("/msg")
    public void msg(String msg){
        helloSender1.send(msg);
    }

    @PostMapping("/oneToMore")
    public void oneToMore() throws InterruptedException{
        for(int i = 0;i<10;i++){
            Thread.sleep(1000);
            helloSender1.send();
        }

    }
}