package io.github.slaxnetwork.api.models.views.profile.game.cookieclicker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerUpgradesView(
    @SerialName("test_upgrade")
    val testUpgrade: Int
)