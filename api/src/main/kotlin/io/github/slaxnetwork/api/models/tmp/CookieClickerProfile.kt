package io.github.slaxnetwork.api.models.tmp

import io.github.slaxnetwork.api.models.views.CookieClickerProfileView
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfile(
    val id: Int,

    val cookies: Int
) {
    fun toView() = CookieClickerProfileView(
        cookies
    )
}