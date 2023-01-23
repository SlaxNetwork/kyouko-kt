package io.github.slaxnetwork.api.wrapper

import io.github.slaxnetwork.api.models.views.profile.ProfileView
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.wrapper.utils.bodyAsResult
import io.ktor.client.request.*
import java.util.UUID

object ProfileAPI {
    suspend fun getProfileByUUID(uuid: UUID): Result<ProfileView> {
        return client.get {
            url("/profile")
            parameter("uuid", uuid.toString())
        }.bodyAsResult()
    }

    suspend fun getProfileByUsername(name: String): Result<ProfileView> {
        return client.get {
            url("/profile")
            parameter("username", name)
        }.bodyAsResult()
    }
}