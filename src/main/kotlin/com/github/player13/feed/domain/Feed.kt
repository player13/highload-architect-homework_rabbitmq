package com.github.player13.feed.domain

import com.github.player13.feed.domain.Feed.FeedId
import java.io.Serializable
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(FeedId::class)
data class Feed(
    @Id
    val follower: String,
    @Id
    val postId: UUID,
    val author: String,
    val title: String,
    val content: String,
    val timestamp: Instant,
) {

    class FeedId : Serializable {
        lateinit var follower: String
        lateinit var postId: UUID
    }
}
