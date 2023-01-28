package io.github.slaxnetwork.api.dto.game.cookieclicker

import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfile(
    val cookies: Int,

    val upgrades: CookieClickerUpgrades
)