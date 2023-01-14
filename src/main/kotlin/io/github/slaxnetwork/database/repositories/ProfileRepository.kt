package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.tmp.GameProfile
import io.github.slaxnetwork.api.models.tmp.Profile
import io.github.slaxnetwork.api.models.tmp.requests.ProfileCreationRequest
import org.litote.kmongo.Id
import java.util.*

interface ProfileRepository {
    suspend fun create(profile: ProfileCreationRequest): Profile

    suspend fun findByName(name: String): Profile?

    suspend fun findByUUID(uuid: UUID): Profile?

    suspend fun getGameProfile(uuid: UUID): GameProfile?

    suspend fun addPunishment(uuid: UUID, id: Id<Punishment>)

    suspend fun getAll(): List<Profile>
}