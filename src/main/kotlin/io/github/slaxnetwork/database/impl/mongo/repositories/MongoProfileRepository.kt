package io.github.slaxnetwork.database.impl.mongo.repositories

import io.github.slaxnetwork.api.models.dto.ProfileDTO
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.push
import java.util.*

class MongoProfileRepository(db: CoroutineDatabase) : ProfileRepository {
    private val collection = db.getCollection<Profile>("profiles")
    override suspend fun create(profile: Profile): ProfileDTO {
        TODO("Not yet implemented")
    }

    override suspend fun findByName(name: String): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun findByUUID(uuid: UUID): ProfileDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addPunishment(uuid: UUID, id: Id<Punishment>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<ProfileDTO> {
        TODO("Not yet implemented")
    }

}