package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentRevert
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentType
import org.litote.kmongo.Id
import java.util.UUID

interface PunishmentRepository {
    suspend fun issue(punishment: Punishment): Punishment

    suspend fun revert(id: Id<Punishment>, revert: PunishmentRevert)

    suspend fun getAllFromPlayer(issued: UUID): List<Punishment>

    suspend fun getAllFromPlayer(issued: UUID, type: PunishmentType): List<Punishment>

    suspend fun getActivePunishment(issued: UUID, type: PunishmentType): Punishment?

    suspend fun findById(id: Id<Punishment>): Punishment?
}