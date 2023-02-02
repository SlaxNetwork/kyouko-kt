package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.database.models.profile.game.GameProfileModel
import java.util.UUID

interface GameProfileRepository {
    suspend fun create(): Int

    suspend fun getGameProfileId(uuid: UUID): Int?

    suspend fun findById(id: Int): GameProfileModel?

    suspend fun findByUUID(uuid: UUID): GameProfileModel?

    suspend fun setCookieClickerProfileId(uuid: UUID, id: Int)
}