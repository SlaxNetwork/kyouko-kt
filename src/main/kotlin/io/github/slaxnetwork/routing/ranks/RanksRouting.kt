package io.github.slaxnetwork.routing.ranks

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.database.models.rank.RankModel
import io.github.slaxnetwork.database.repositories.RanksRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.patch
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.ranksRouting() {
    val ranksRepository by inject<RanksRepository>()

    get<RanksResource> {
        call.respond(ranksRepository.getAll().map { it.toDTO() })
    }

    get<RanksResource.Id> { ctx ->
        val rank = ranksRepository.findById(ctx.id)
            ?: return@get call.respond(RouteError.NotFound)
        call.respond(rank.toDTO())
    }

    authenticate(
        "bearer",
        optional = false
    ) {
        post<RanksResource> {
            val rank = call.receiveNullable<RankModel>()
                ?: throw RouteError.InvalidBody

            if(ranksRepository.findById(rank.id) != null) {
                throw RouteError.Exists
            }

            ranksRepository.create(rank)
            call.respond(rank.toDTO())
        }

        patch<RanksResource.Id> { ctx ->
            val rank = call.receiveNullable<RankModel>()
                ?: throw RouteError.InvalidBody

            if(ranksRepository.findById(rank.id) == null) {
                throw RouteError.NotFound
            }

            ranksRepository.update(ctx.id, rank)
            call.respond(rank.toDTO())
        }
    }
}