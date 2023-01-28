package io.github.slaxnetwork.api.wrapper

import io.github.slaxnetwork.api.dto.Profile
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.wrapper.utils.bodyAsResult
import io.ktor.client.request.*
import java.util.*

object ProfileAPI {
    suspend fun getProfileByUUID(uuid: UUID): Result<Profile> {
        return client.get {
            url("/profile")
            parameter("uuid", uuid.toString())
        }.bodyAsResult()
    }

    suspend fun getProfileByUsername(name: String): Result<Profile> {
        return client.get {
            url("/profile")
            parameter("username", name)
        }.bodyAsResult()
    }
}