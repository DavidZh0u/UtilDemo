package com.o98k.mqtt.subscribe;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈MQTT接收消息处理〉
 *
 * @author lenovo
 * @create 2018/6/4
 * @since 1.0.0
 */
@Configuration
@IntegrationComponentScan
public class MqttReceiveConfig {
 
    @Value("${spring.mqtt.username}")
    private String username;
 
    @Value("${spring.mqtt.password}")
    private String password;
 
    @Value("${spring.mqtt.url}")
    private String hostUrl;
 
    @Value("${spring.mqtt.client.id}")
    private String clientId;
 
    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;
 
    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout ;   //连接超时
 
 
    @Bean
    public MqttConnectOptions getMqttConnectOptions(){
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }
 
    //接收通道
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    //配置client,监听的topic 
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(),
                        "hz-msg-test","api/huazhuxiyi/#","hello","hz-msg-dev","api/huazhuxiyi/login/70874","api/huazhuxiyi/logout/70874");
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
 
    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String type = topic.substring(topic.lastIndexOf("/")+1, topic.length());
                if("hz-msg-test".equalsIgnoreCase(topic)){
                    System.out.println("[test-msg]:"+message.getPayload().toString());
                }else if("hz-msg-dev".equalsIgnoreCase(topic)){
                    System.out.println("[dev-msg]:"+message.getPayload().toString());
                }else if("hello".equalsIgnoreCase(topic)){
                    System.out.println("[hello]:"+message.getPayload().toString());
                }else if("api/huazhuxiyi/#".equalsIgnoreCase(topic)){
                    System.out.println("[洗衣机消息订阅]:"+message.getPayload().toString());
                }else if("api/huazhuxiyi/login/70874".equalsIgnoreCase(topic)){
                    System.out.println("[上线设备]:"+message.getPayload().toString());
                }else if("api/huazhuxiyi/logout/70874".equalsIgnoreCase(topic)){
                    System.out.println("[下线设备]:"+message.getPayload().toString());
                }
            }
        };
    }
}