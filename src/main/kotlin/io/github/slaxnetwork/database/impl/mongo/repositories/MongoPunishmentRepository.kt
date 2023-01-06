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
        collection.insertOne(punishment)
        profileRepository.addPunishment(punishment.issued, punishment.id)
        return punishment
    }

    override suspend fun revert(id: Id<Punishment>, revert: PunishmentRevert) {
        collection.updateOneById(id, setValue(Punishment::revert, revert))
    }

    override suspend fun getAllFromPlayer(issued: UUID): List<Punishment> {
        val profile = profileRepository.findByUUID(issued)
            ?: return emptyList()

        return profile.punishments.mapNotNull {
            collection.findOneById(it)
        }
    }

    override suspend fun getAllFromPlayer(issued: UUID, type: PunishmentType): List<Punishment> {
        return getAllFromPlayer(issued).filter {
            it.type == type
        }
    }

    override suspend fun getActivePunishment(issued: UUID, type: PunishmentType): Punishment? {
        return getAllFromPlayer(issued).firstOrNull {
            it.type == type && it.active
        }
    }

    override suspend fun findById(id: Id<Punishment>): Punishment? {
        return collection.findOneById(id)
    }
}