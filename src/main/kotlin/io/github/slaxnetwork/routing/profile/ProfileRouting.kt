package io.github.slaxnetwork.routing.profile

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.database.repositories.ProfilePreferencesRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.utils.MojangUtils
import io.github.slaxnetwork.utils.authorized
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.profileRouting() {
    val profileRepository by inject<ProfileRepository>()
    val preferencesRepository by inject<ProfilePreferencesRepository>()

    authenticate(
        "bearer",
        optional = true
    ) {
        get<ProfileResource> { ctx ->
            var profile = if(ctx.uuid != null) {
                profileRepository.findByUUID(UUID.fromString(ctx.uuid))
            } else if(ctx.username != null) {
                // TODO: 1/27/2023 pls impl
                throw RouteError.NotFound
            } else {
                // TODO: 1/14/2023 don't hardcode.
                throw RouteError(422, "uuid or username not passed.")
            }

            if(profile == null && call.authorized) {
                val mojangProfile = MojangUtils.getProfile(ctx.query ?: throw RouteError.NotFound)
                    .getOrThrow()

                profile = profileRepository.create(mojangProfile.uuid)
            }

            // TODO: 1/27/2023 clean
            val preferences = preferencesRepository.findById(profile?.preferencesId ?: throw RouteError.NotFound)
                ?: throw RouteError.NotFound

            call.respond(profile.toDTO(
                preferences.toDTO()
            ))
        }
    }
}