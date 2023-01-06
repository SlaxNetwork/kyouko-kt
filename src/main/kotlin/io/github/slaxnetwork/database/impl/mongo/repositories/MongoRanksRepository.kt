package io.github.slaxnetwork.database.impl.mongo.repositories

import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.database.repositories.RanksRepository
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoRanksRepository(db: CoroutineDatabase) : RanksRepository {
    private val collection = db.getCollection<Rank>("ranks")

    override suspend fun findById(id: String): Rank? {
        return collection.findOneById(id)
    }

    override suspend fun create(rank: Rank): Rank {
        collection.insertOne(rank)
        return rank
    }

    override suspend fun update(rankId: String, rank: Rank) {
        collection.replaceOneById(rankId, rank)
    }

    override suspend fun getAll(): List<Rank> {
        return collection.find().toList()
    }
}