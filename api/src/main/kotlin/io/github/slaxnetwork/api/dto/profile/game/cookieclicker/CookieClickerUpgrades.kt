package io.github.slaxnetwork.api.dto.profile.game.cookieclicker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerUpgrades(
    @SerialName("test_upgrade")
    val testUpgrade: Int
)