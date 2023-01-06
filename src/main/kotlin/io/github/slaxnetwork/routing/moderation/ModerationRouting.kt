package io.github.slaxnetwork.routing.moderation

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.moderationRouting() {
    punishmentRouting()
}