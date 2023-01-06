package io.github.slaxnetwork.database.impl.mongo.repositories

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

    override suspend fun findByName(name: String): Profile? {
        // TODO: 12/31/2022 please handle case in-sensitive
        return collection.findOne(Profile::username eq name)
    }

    override suspend fun findByUUID(uuid: UUID): Profile? {
        return collection.findOneById(uuid)
    }

    override suspend fun create(profile: Profile): Profile {
        collection.insertOne(profile)
        return profile
    }

    override suspend fun addPunishment(uuid: UUID, id: Id<Punishment>) {
        collection.updateOneById(uuid, push(Profile::punishments, id))
    }

    override suspend fun getAll(): List<Profile> {
        return collection.find().toList()
    }
}