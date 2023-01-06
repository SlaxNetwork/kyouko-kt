package io.github.slaxnetwork.utils

import io.github.slaxnetwork.api.exceptions.RouteError
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class SimpleMojangProfile(
    @Contextual
    @SerialName("id")
    val uuid: UUID,

    @SerialName("name")
    val username: String
)

// TODO: 1/6/2023 Look into Mojang rate limiting the API.

object MojangUtils {
    suspend fun getProfileFromUUID(uuid: UUID): Result<SimpleMojangProfile> {
        val res = client.get {
            url {
                host = "sessionserver.mojang.com"
                path("session", "minecraft", "profile", uuid.toString())
                protocol = URLProtocol.HTTPS
            }
        }

        if(!res.status.isSuccess() || res.status == HttpStatusCode.NoContent) {
            return Result.failure(RouteError(res.status.value, "mojang api uuid fetch failed for $uuid"))
        }
        return Result.success(res.body())
    }

    suspend fun getProfileFromName(name: String): Result<SimpleMojangProfile> {
        val res = client.get {
            url {
                host = "api.mojang.com"
                path("users", "profiles", "minecraft", name)
                protocol = URLProtocol.HTTPS
            }
        }

        if(!res.status.isSuccess() || res.status == HttpStatusCode.NoContent) {
            return Result.failure(RouteError(res.status.value, "mojang api name fetch failed for $name"))
        }
        return Result.success(res.body())
    }
}