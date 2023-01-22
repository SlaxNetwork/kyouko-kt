package io.github.slaxnetwork.api.models.views.profile.game.cookieclicker

import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfileView(
    val cookies: Int,

    val upgrades: CookieClickerUpgradesView
)