package io.github.slaxnetwork.routing.news

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

private const val EXAMPLE_AUTHOR_ID = 737340275030097921
private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

fun Route.newsRouting() {
    get<NewsResource> {
        @Serializable
        data class ExamplePost(
            val title: String,
            val author: Long,
            val content: String,

            val timestamp: Long
        )

        val posts = setOf(
            ExamplePost("Example Post #1", EXAMPLE_AUTHOR_ID, LOREM_IPSUM, System.currentTimeMillis()),
            ExamplePost("Example Post #2", EXAMPLE_AUTHOR_ID, LOREM_IPSUM, System.currentTimeMillis() - 2000L),
            ExamplePost("Example Post #2", EXAMPLE_AUTHOR_ID, LOREM_IPSUM, System.currentTimeMillis() - 10000L)
        )

        call.respond(posts)
    }
}