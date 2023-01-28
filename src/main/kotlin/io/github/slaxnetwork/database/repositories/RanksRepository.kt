package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.rank.RankModel

interface RanksRepository {
    suspend fun findById(id: String): RankModel?

    suspend fun create(rank: RankModel): RankModel

    suspend fun update(rankId: String, rank: RankModel)

    suspend fun getAll(): List<RankModel>
}