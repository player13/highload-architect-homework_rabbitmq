package com.github.player13.feed.controller

import com.github.player13.feed.domain.Subscription
import com.github.player13.feed.service.SubscriptionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController(
    private val subscriptionService: SubscriptionService,
) {

    @PostMapping("/subscriptions")
    fun public(@RequestBody subscription: Subscription): Subscription =
        subscriptionService.create(subscription)
}
