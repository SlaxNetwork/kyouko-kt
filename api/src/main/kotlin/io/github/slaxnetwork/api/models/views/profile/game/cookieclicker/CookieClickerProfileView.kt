package io.github.slaxnetwork.api.models.views.profile.game.cookieclicker

import io.github.slaxnetwork.api.models.views.profile.game.PopulatedGameProfileView
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfileView(
    val cookies: Int,

    val upgrades: CookieClickerUpgradesView
) : PopulatedGameProfileView