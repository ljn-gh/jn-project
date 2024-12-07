package com.jianan.demomodule.test.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Author: jn
 * @Date: 2024/12/7
 * @description
 **/
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "${rocketmq.consumer.topic}")
public class MqConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        String messageBody =  new String(messageExt.getBody(), StandardCharsets.UTF_8);
        System.out.println("consumer receive message: " + messageBody);
    }
}
