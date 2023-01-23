package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.game.GameProfile
import java.util.UUID

interface GameProfileRepository {
    suspend fun create(): Int

    suspend fun findById(id: Int): GameProfile?

    suspend fun findByUUID(uuid: UUID): GameProfile?
}