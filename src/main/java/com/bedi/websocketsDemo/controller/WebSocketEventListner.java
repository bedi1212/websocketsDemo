package com.bedi.websocketsDemo.controller;


import com.bedi.websocketsDemo.model.ChatMessage;
import com.bedi.websocketsDemo.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListner.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebsocketConnectListner (final SessionConnectedEvent event){
        LOGGER.info("we have a new connect chat ");

    }
    @EventListener
    public void handleWebSocketDisconnectListner(final SessionDisconnectEvent event){

        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) headerAccessor.getSessionAttributes().get("username");
        final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("/topic/public", chatMessage);
    }
}
