package io.github.slaxnetwork.routing.game

import io.github.slaxnetwork.routing.game.cookieclicker.cookieClickerRouting
import io.ktor.server.routing.*

fun Route.gameRouting() {
    cookieClickerRouting()
}