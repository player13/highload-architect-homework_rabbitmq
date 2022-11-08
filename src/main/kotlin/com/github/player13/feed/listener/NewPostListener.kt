package com.github.player13.feed.listener

import com.github.player13.feed.domain.Post
import com.github.player13.feed.service.SubscriptionService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class NewPostListener(
    private val subscriptionService: SubscriptionService,
    private val rabbitTemplate: RabbitTemplate,
) {

    @RabbitListener(queues = ["post"])
    fun onNewPost(post: Post) {
        subscriptionService.getFollowers(post.author).forEach { follower ->
            rabbitTemplate.convertAndSend("feed", follower, post)
        }
    }
}
