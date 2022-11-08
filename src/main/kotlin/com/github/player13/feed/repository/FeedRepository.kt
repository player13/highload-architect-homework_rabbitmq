package com.github.player13.feed.repository

import com.github.player13.feed.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedRepository : JpaRepository<Feed, String> {
    fun findLast10ByFollowerOrderByTimestampAsc(follower: String): List<Feed>
}
