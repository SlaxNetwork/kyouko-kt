package io.github.slaxnetwork.database.repositories.game

import io.github.slaxnetwork.api.models.profile.game.cookieclicker.CookieClickerProfile
import java.util.UUID

interface CookieClickerRepository {
    suspend fun create(uuid: UUID): Int

    suspend fun findById(id: Int): CookieClickerProfile?

    suspend fun findByUUID(uuid: UUID): CookieClickerProfile?
}