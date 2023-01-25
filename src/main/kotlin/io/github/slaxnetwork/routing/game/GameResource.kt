package io.github.slaxnetwork.routing.game

import io.github.slaxnetwork.utils.toUUID
import io.ktor.resources.*
import kotlinx.serialization.SerialName
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

            @SerialName("uuid")
            val uuidStr: String
        ) {
            val uuid get() = uuidStr.toUUID()
        }
    }
}