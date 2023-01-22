package io.github.slaxnetwork.routing.game

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/games") @Serializable
class GameResource {
    @Resource("cookieclicker") @Serializable
    class CookieClicker(
        val parent: GameResource = GameResource()
    ) {
        @Resource("profile") @Serializable
        class Profile(
            val parent: CookieClicker,

            val uuid: String
        )
    }
}