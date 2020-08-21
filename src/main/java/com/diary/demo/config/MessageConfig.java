package com.diary.demo.config;

import com.diary.demo.sender.MessageSender;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("message.topic");
    }

    @Bean
    public MessageSender sender() {
        return new MessageSender();
    }

}