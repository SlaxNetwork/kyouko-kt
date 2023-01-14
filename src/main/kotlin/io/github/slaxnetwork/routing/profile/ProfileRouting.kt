package io.github.slaxnetwork.routing.profile

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.utils.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.profileRouting() {
    val profileRepository by inject<ProfileRepository>()

    authenticate(
        "bearer",
        optional = true
    ) {
        get<ProfileResource> { ctx ->
            var profile = if(ctx.uuid != null) {
                profileRepository.findByUUID(UUID.fromString(ctx.uuid))
            } else if(ctx.username != null) {
                throw RouteError.NotFound
            } else {
                throw RouteError(422, "uuid or username not passed.")
            }

            if(profile == null && call.authorized) {
                val mojangProfile = MojangUtils.getProfile(ctx.query ?: throw RouteError.NotFound)
                    .getOrThrow()

                profile = profileRepository.create(Profile(
                    mojangProfile.uuid,
                    mojangProfile.username
                ))

            } else if(profile == null) {
                throw RouteError.NotFound
            }

            call.respond(profile)
        }
    }
}