package com.PenguinGangT2.Backend.controller;

import org.springframework.stereotype.Controller;

import com.PenguinGangT2.Backend.message.MessageModel;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

@Controller
public class ChatController {
    
    @MessageMapping("/chat.send")
    @SendTo("/topic/global")
    public MessageModel sendMessage(@Payload MessageModel chatMessage){
        return chatMessage;
    }
}
