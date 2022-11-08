package com.github.player13.feed.domain

import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Post(
    @Id
    val id: UUID = UUID.randomUUID(),
    val timestamp: Instant = Instant.now(),
    val author: String,
    val title: String,
    val content: String,
)
