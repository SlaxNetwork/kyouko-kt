package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.game.GameProfile
import io.github.slaxnetwork.api.models.profile.Profile
import java.util.*

interface ProfileRepository {
    suspend fun create(uuid: UUID): Profile

    suspend fun findByName(name: String): Profile?

    suspend fun findByUUID(uuid: UUID): Profile?

    suspend fun getGameProfile(uuid: UUID): GameProfile?

    suspend fun getAll(): List<Profile>
}