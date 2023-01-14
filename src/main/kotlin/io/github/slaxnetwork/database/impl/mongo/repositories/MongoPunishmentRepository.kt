package io.github.slaxnetwork.database.impl.mongo.repositories

import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentRevert
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentType
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.repositories.PunishmentRepository
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.util.*

class MongoPunishmentRepository(
    db: CoroutineDatabase,
    private val profileRepository: ProfileRepository
): PunishmentRepository {
    private val collection = db.getCollection<Punishment>("punishments")
    override suspend fun issue(punishment: Punishment): Punishment {
        TODO("Not yet implemented")
    }

    override suspend fun revert(id: Id<Punishment>, revert: PunishmentRevert) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFromPlayer(issued: UUID): List<Punishment> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFromPlayer(issued: UUID, type: PunishmentType): List<Punishment> {
        TODO("Not yet implemented")
    }

    override suspend fun getActivePunishment(issued: UUID, type: PunishmentType): Punishment? {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Id<Punishment>): Punishment? {
        TODO("Not yet implemented")
    }

}