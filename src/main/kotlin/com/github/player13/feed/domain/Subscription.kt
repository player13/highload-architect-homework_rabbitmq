package com.github.player13.feed.domain

import com.github.player13.feed.domain.Subscription.SubscriptionId
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(SubscriptionId::class)
data class Subscription(
    @Id
    val author: String,
    @Id
    val follower: String,
) {

    class SubscriptionId : Serializable {
        lateinit var author: String
        lateinit var follower: String
    }
}
