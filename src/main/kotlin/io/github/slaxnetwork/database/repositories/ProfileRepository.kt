package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import org.litote.kmongo.Id
import java.util.UUID

interface ProfileRepository {
    suspend fun create(profile: Profile): Profile

    suspend fun findByName(name: String): Profile?

    suspend fun findByUUID(uuid: UUID): Profile?

    suspend fun addPunishment(uuid: UUID, id: Id<Punishment>)

    suspend fun getAll(): List<Profile>
}