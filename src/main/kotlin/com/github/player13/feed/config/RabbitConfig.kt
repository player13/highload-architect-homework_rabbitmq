package com.github.player13.feed.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun feedExchange(): TopicExchange =
        ExchangeBuilder.topicExchange("feed").build()

    @Bean
    fun feedQueue(): Queue =
        QueueBuilder.durable("feed").build()

    @Bean
    fun feedBinding(): Binding =
        BindingBuilder.bind(feedQueue()).to(feedExchange()).with("#")

    @Bean
    fun postExchange(): TopicExchange =
        ExchangeBuilder.topicExchange("post").build()

    @Bean
    fun postQueue(): Queue =
        QueueBuilder.durable("post").build()

    @Bean
    fun postBinding(): Binding =
        BindingBuilder.bind(postQueue()).to(postExchange()).with("#")

    @Bean
    fun declarables(): Declarables =
        Declarables(
            feedExchange(),
            postExchange(),
            postQueue(),
            postBinding(),
        )

    @Bean
    fun objectMapper(): ObjectMapper =
        jsonMapper {
            addModule(kotlinModule())
            addModule(JavaTimeModule())
        }

    @Bean
    fun messageConverter(): MessageConverter =
        Jackson2JsonMessageConverter(objectMapper())
}
