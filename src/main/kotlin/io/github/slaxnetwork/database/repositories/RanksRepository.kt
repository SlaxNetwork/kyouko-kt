package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.rank.Rank

interface RanksRepository {
    suspend fun findById(id: String): Rank?

    suspend fun create(rank: Rank): Rank

    suspend fun update(rankId: String, rank: Rank)

    suspend fun getAll(): List<Rank>
}