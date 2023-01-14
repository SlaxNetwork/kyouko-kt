package io.github.slaxnetwork.routing.profile

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.gameProfileRouting() {
    val gameProfileRepository by inject<GameProfileRepository>()
    val re1 by inject<CookieClickerRepository>()

    get<ProfileResource.Id.Games> { ctx ->
        val uuid = try {
            UUID.fromString(ctx.uuid)
        } catch(ex: IllegalArgumentException) {
            throw ex
        }

        val gameProfile = gameProfileRepository.findByUUIDAndPopulate(uuid, ctx.type)
            ?: throw RouteError.NotFound

        call.respond(gameProfile)
    }
}