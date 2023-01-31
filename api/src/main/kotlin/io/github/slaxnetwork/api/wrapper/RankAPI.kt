package io.github.slaxnetwork.api.wrapper

import io.github.slaxnetwork.api.dto.rank.Rank
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.wrapper.utils.bodyAsResult
import io.ktor.client.request.*

object RankAPI {
    suspend fun getAll(): Result<List<Rank>> {
        return client.get {
            url("/ranks")
        }.bodyAsResult()
    }
}