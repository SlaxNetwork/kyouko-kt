package io.github.slaxnetwork.api.models.views.profile.game

import io.github.slaxnetwork.api.models.views.profile.game.cookieclicker.CookieClickerProfileView
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GameProfileView(
    @SerialName("cookie_clicker")
    val cookieClicker: CookieClickerProfileView
)