package com.github.player13.feed.controller

import com.github.player13.feed.domain.Feed
import com.github.player13.feed.service.FeedService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FeedController(
    private val feedService: FeedService,
) {

    @GetMapping("/feeds/{follower}/posts")
    fun get(@PathVariable follower: String): List<Feed> =
        feedService.getLastTenFeedPosts(follower)
}
