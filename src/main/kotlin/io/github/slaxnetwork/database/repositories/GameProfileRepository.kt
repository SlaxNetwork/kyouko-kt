package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.game.GameProfile
import io.github.slaxnetwork.api.models.views.profile.game.PopulatedGameProfileView
import java.util.UUID

interface GameProfileRepository {
    suspend fun create(): Int

    suspend fun find(id: Int): GameProfile?

    suspend fun findByIdAndPopulate(id: Int, type: String): GameProfile?

    suspend fun findByUUID(uuid: UUID): GameProfile?

    suspend fun findByUUIDAndPopulate(uuid: UUID, game: String): PopulatedGameProfileView?
}