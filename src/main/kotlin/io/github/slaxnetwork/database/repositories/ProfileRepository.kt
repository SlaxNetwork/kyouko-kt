package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.game.GameProfileModel
import io.github.slaxnetwork.api.models.profile.ProfileModel
import java.util.*

interface ProfileRepository {
    suspend fun create(uuid: UUID): ProfileModel

    suspend fun findByName(name: String): ProfileModel?

    suspend fun findByUUID(uuid: UUID): ProfileModel?

    suspend fun getGameProfile(uuid: UUID): GameProfileModel?

    suspend fun getAll(): List<ProfileModel>
}