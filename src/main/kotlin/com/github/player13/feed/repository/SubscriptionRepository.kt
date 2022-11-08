package com.github.player13.feed.repository

import com.github.player13.feed.domain.Subscription
import com.github.player13.feed.domain.Subscription.SubscriptionId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository : JpaRepository<Subscription, SubscriptionId> {
    fun findByAuthor(author: String): List<Subscription>
}
