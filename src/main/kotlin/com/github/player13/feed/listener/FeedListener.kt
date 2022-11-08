package com.github.player13.feed.listener

import com.github.player13.feed.domain.Feed
import com.github.player13.feed.domain.Post
import com.github.player13.feed.service.FeedService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders.RECEIVED_ROUTING_KEY
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class FeedListener(
    private val feedService: FeedService,
) {

    @RabbitListener(queues = ["feed"])
    fun materializeFeed(@Payload post: Post, @Header(RECEIVED_ROUTING_KEY) user: String) {
        feedService.create(Feed(user, post.id, post.author, post.title, post.content, post.timestamp))
    }
}
