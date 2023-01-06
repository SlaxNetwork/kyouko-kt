package io.github.slaxnetwork.routing.moderation

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentRevert
import io.github.slaxnetwork.database.repositories.PunishmentRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.punishmentRouting() {
    val punishmentRepository by inject<PunishmentRepository>()

    authenticate(
        "bearer",
        optional = false
    ) {
        get<ModerationResource.Punishment.Id> { ctx ->
            val uuid = UUID.fromString(ctx.uuid)

            if(ctx.active == true && ctx.type != null) {
                val punishment = punishmentRepository.getActivePunishment(uuid, ctx.type)
                    ?: throw RouteError.NotFound

                call.respond(punishment)
            }

            val punishments = if(ctx.type != null) {
                punishmentRepository.getAllFromPlayer(uuid, ctx.type)
            } else {
                punishmentRepository.getAllFromPlayer(uuid)
            }

            call.respond(punishments)
        }

        // permissions verified bukkit server-side.
        post<ModerationResource.Punishment.Id.Issue> {
            val punishment = call.receiveNullable<Punishment>()
                ?: throw RouteError.InvalidBody

            val active = punishmentRepository.getActivePunishment(punishment.issued, punishment.type)
            if(active != null) {
                throw RouteError.Exists
            }

            punishmentRepository.issue(punishment)
            call.respond(punishment)
        }

        delete<ModerationResource.Punishment.Id.Revert> {ctx ->
            val revert = call.receiveNullable<PunishmentRevert>()
                ?: throw RouteError.InvalidBody

            val punishment = punishmentRepository.getActivePunishment(
                UUID.fromString(ctx.parent.uuid),
                ctx.type
            ) ?: throw RouteError.NotFound

            punishmentRepository.revert(punishment.id, revert)
            call.respond(revert)
        }
    }
}