package com.diary.demo.service;

import com.diary.demo.entity.Message;
import com.diary.demo.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageSender messageSender;

    public void sendMessage(Message message){
        messageSender.sendEmail(message);
    }
}