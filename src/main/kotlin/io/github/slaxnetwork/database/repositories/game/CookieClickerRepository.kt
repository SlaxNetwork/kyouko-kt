package io.github.slaxnetwork.database.repositories.game

import io.github.slaxnetwork.database.models.profile.game.cookieclicker.CookieClickerProfileModel
import java.util.UUID

interface CookieClickerRepository {
    suspend fun create(uuid: UUID): Int

    suspend fun findById(id: Int): CookieClickerProfileModel?

    suspend fun findByUUID(uuid: UUID): CookieClickerProfileModel?
}