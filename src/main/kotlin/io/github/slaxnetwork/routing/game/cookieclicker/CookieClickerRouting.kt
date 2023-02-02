package io.github.slaxnetwork.routing.game.cookieclicker

import io.github.slaxnetwork.api.dto.profile.game.cookieclicker.CookieClickerUpgrades
import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.database.models.profile.game.cookieclicker.CookieClickerUpgradesModel
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import io.github.slaxnetwork.routing.game.GameResource
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

// TODO: 1/21/2023 auth
fun Route.cookieClickerRouting() {
    val cookieClickerRepository by inject<CookieClickerRepository>()

    get<GameResource.CookieClicker.Profile> { ctx ->
        val uuid = ctx.uuid
            ?: throw RouteError.InvalidId

        val profile = cookieClickerRepository.findByUUID(uuid)
            ?: cookieClickerRepository.findById(cookieClickerRepository.create(uuid))
            ?: throw RuntimeException("failed to create profile for $uuid")

        call.respond(profile.toDTO(CookieClickerUpgradesModel(0)))
    }
}