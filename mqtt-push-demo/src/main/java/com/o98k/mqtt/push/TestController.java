package com.o98k.mqtt.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
 
    @Autowired
    private MqttGateway mqttGateway;
 
    @RequestMapping("/sendMqtt")
    public String sendMqtt(HttpServletRequest request){
        String sendData = request.getParameter("sendData");
        String topic = request.getParameter("topic");
        mqttGateway.sendToMqtt(sendData,topic);
        return "OK";
    }
}
