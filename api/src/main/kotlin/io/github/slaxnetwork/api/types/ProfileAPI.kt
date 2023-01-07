package io.github.slaxnetwork.api.types

import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.utils.wrapResponse
import io.ktor.client.request.*
import java.util.UUID

object ProfileAPI {
    /**
     * Get the profile of a player by their UUID.
     * Automatically creates a [Profile] if one doesn't
     * already exist for the user.
     *
     * @param uuid Player UUID
     *
     * @return the profile from found by [uuid][UUID],
     * [RouteError][io.github.slaxnetwork.api.exceptions.RouteError] if
     * one was not found and a profile could not be created.
     */
    suspend fun getProfileByUUID(uuid: UUID): Result<Profile> {
        return getProfile(uuid.toString(), false)
    }

    /**
     * Get the profile of a player by their Username.
     * Automatically creates a [Profile] if one doesn't
     * already exist for the user.
     *
     * @param name Player Username
     *
     * @return the profile from found by [username][String],
     * [RouteError][io.github.slaxnetwork.api.exceptions.RouteError] if
     * one was not found and a profile could not be created.
     */
    suspend fun getProfileByUsername(name: String): Result<Profile> {
        return getProfile(name, true)
    }

    private suspend fun getProfile(query: String, byUsername: Boolean): Result<Profile> {
        return wrapResponse(client.get("profile/$query") {
            parameter("byUsername", byUsername)
        })
    }
}