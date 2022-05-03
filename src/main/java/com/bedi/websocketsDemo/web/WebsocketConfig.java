package com.bedi.websocketsDemo.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {


    public void registerStomEndpoints(StompEndpointRegistry registry){

        registry.addEndpoint("/chatExample").withSockJS();

    }

    public void configureMessageBroker(final MessageBrokerRegistry registry){

        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

}
