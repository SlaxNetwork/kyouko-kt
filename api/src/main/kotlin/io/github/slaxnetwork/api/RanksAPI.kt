package io.github.slaxnetwork.api

import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.utils.wrapResponse
import io.ktor.client.request.*

object RanksAPI {
    suspend fun getAllRanks(): Result<Profile> {
        return wrapResponse(client.get("ranks"))
    }
}