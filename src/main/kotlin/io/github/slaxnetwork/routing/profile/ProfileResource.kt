package io.github.slaxnetwork.routing.profile

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/profile") @Serializable
class ProfileResource {
    @Resource("{query}") @Serializable
    class Query(
        val parent: ProfileResource = ProfileResource(),
        val query: String,
        val byUsername: Boolean = false
    )
}