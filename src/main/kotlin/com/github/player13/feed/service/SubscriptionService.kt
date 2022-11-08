package com.github.player13.feed.service

import com.github.player13.feed.domain.Subscription
import com.github.player13.feed.repository.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
) {
    fun create(subscription: Subscription): Subscription =
        subscriptionRepository.save(subscription)

    fun getFollowers(author: String): List<String> =
        subscriptionRepository.findByAuthor(author)
            .map { it.follower }
}
