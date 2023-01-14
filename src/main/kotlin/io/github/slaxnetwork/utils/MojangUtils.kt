package io.github.slaxnetwork.utils

import io.github.slaxnetwork.api.exceptions.RouteError
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SimpleMojangProfile(
    @Contextual
    val uuid: UUID,

    val username: String
)

object MojangUtils {
    suspend fun getProfile(uuid: UUID): Result<SimpleMojangProfile> {
        return getProfile(uuid.toString())
    }

    suspend fun getProfile(query: String): Result<SimpleMojangProfile> {
        val res = client.get {
            url {
                host = "api.ashcon.app"
                path("mojang", "v2", "user", query)
                protocol = URLProtocol.HTTPS
            }
        }

        // try using mojangs api to get data.
        if(!res.status.isSuccess()) {
            return if(query.length > 16) {
                getProfileFromUUID(UUID.fromString(query))
            } else {
                getProfileFromName(query)
            }
        }

        return Result.success(
            res.body<SimpleProxyAshconProfile>()
                .toMojangProfile()
        )
    }

    private suspend fun getProfileFromUUID(uuid: UUID): Result<SimpleMojangProfile> {
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
        return Result.success(
            res.body<SimpleProxyMojangProfile>()
                .toMojangProfile()
        )
    }

    private suspend fun getProfileFromName(name: String): Result<SimpleMojangProfile> {
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
        return Result.success(
            res.body<SimpleProxyMojangProfile>()
                .toMojangProfile()
        )
    }

    @Serializable
    private data class SimpleProxyAshconProfile(
        @Contextual
        override val uuid: UUID,

        override val username: String
    ): ISimpleProfile

    @Serializable
    private data class SimpleProxyMojangProfile(
        @Contextual
        @SerialName("id")
        override val uuid: UUID,

        @SerialName("name")
        override val username: String
    ): ISimpleProfile

    private interface ISimpleProfile {
        val uuid: UUID

        val username: String

        fun toMojangProfile() = SimpleMojangProfile(uuid, username)
    }
}