package io.github.slaxnetwork.api.models.views

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameProfileView(
    @SerialName("cookie_clicker")
    val cookieClicker: CookieClickerProfileView? = null
)