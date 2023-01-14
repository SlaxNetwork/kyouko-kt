package io.github.slaxnetwork.models

import io.github.slaxnetwork.api.models.profile.game.CookieClickerProfile
import io.github.slaxnetwork.api.models.profile.game.GameProfile
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

enum class NetworkGames(
    val id: String,
    val tableName: String,

    val associatedGameProfile: KClass<*>,
    val gameProfileIdAccessor: KProperty1<GameProfile, Any?>
) {
    COOKIE_CLICKER("cookieclicker", "CookieClickerProfile", CookieClickerProfile::class, GameProfile::cookieClickerProfileId);

    companion object {
        fun getById(id: String): NetworkGames? =
            NetworkGames.values().firstOrNull { it.id.equals(id, true) }
    }
}