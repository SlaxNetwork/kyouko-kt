package io.github.slaxnetwork.routing.profile

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/profile") @Serializable
class ProfileResource(
    val uuid: String? = null,
    val username: String? = null
) {
    // null if both fail.
    val query get() = uuid ?: username

    @Resource("{uuid}") @Serializable
    class Id(
        val parent: ProfileResource = ProfileResource(),

        val uuid: String
    ) {
        @Resource("games") @Serializable
        class Games(
            val parent: Id,

            val type: String
        ) {
            val uuid get() = parent.uuid
        }
    }
}