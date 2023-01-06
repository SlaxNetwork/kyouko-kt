package io.github.slaxnetwork.routing.profile

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/profile/{query}")
class ProfileResource(
    val query: String,
    val byUsername: Boolean = false
)