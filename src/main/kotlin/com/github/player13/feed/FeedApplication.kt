package com.github.player13.feed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeedApplication

fun main(args: Array<String>) {
    runApplication<FeedApplication>(*args)
}
