package io.github.slaxnetwork.api.models.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id

// TODO: 1/6/2023 make this less bad 
@Serializable
data class GameProfile(
    val sessions: List<Id<String>> = emptyList(),

    val games: GameProfileGames
)

@Serializable
data class GameProfileGames(
    @SerialName("cookie_clicker")
    val cookieClicker: CookieClickerGameProfile? = null
)

@Serializable
data class CookieClickerGameProfile(
    val cookies: Int
)