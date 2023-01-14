package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.tmp.GameProfile

interface GameProfileRepository {
    suspend fun create(): Int

    suspend fun find(id: Int): GameProfile?
}