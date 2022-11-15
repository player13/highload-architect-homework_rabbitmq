package com.github.player13.feed.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    @Value("\${spring.rabbitmq.host}")
    private val rabbitHost: String,
) : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config
            .setApplicationDestinationPrefixes("/app")
            .enableStompBrokerRelay("/exchange")
            .setRelayHost(rabbitHost)
            .setRelayPort(61613)
            .setClientLogin("guest")
            .setClientPasscode("guest")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket").withSockJS()
    }
}
