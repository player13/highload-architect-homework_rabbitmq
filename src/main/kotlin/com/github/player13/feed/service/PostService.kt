package com.github.player13.feed.service

import com.github.player13.feed.domain.Post
import com.github.player13.feed.repository.PostRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val rabbitTemplate: RabbitTemplate,
) {

    @Transactional
    fun create(post: Post): Post =
        postRepository.save(post).also {
            rabbitTemplate.convertAndSend("post", it.author, it)
        }
}
