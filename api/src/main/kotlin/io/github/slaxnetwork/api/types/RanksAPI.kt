package io.github.slaxnetwork.api.types

import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.utils.wrapResponse
import io.ktor.client.request.*

object RanksAPI {
    /**
     * Get a specific ranks data.
     *
     * @param id Rank ID
     * @return the rank found by [id][String], [RouteError][io.github.slaxnetwork.api.exceptions.RouteError]
     * if none were found.
     */
    suspend fun getRankById(id: String): Result<Rank> {
        return wrapResponse(client.get("ranks/$id"))
    }

    /**
     * Get every registered rank in the database.
     *
     * @return a [list][List] of every [Rank].
     */
    suspend fun getAllRanks(): Result<List<Rank>> {
        return wrapResponse(client.get("ranks"))
    }
}