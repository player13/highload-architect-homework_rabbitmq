package com.github.player13.feed.controller

import com.github.player13.feed.domain.Post
import com.github.player13.feed.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
) {

    @PostMapping("/posts")
    fun public(@RequestBody post: Post): Post =
        postService.create(post)
}
