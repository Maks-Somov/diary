package com.diary.demo.sender;

import com.diary.demo.entity.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSender {
    @Autowired
    private TopicExchange topic;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(Message message) {
        String key = "email.message";
        rabbitTemplate.convertAndSend(topic.getName(), key, message);
        System.out.println("message "+message);
    }
}