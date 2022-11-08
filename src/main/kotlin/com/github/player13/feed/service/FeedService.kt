package com.github.player13.feed.service

import com.github.player13.feed.domain.Feed
import com.github.player13.feed.repository.FeedRepository
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val feedRepository: FeedRepository,
) {
    fun create(feed: Feed): Feed =
        feedRepository.save(feed)

    fun getLastTenFeedPosts(follower: String): List<Feed> =
        feedRepository.findLast10ByFollowerOrderByTimestampAsc(follower)
}
