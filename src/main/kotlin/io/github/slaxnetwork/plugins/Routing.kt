package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.api.exceptions.RouteError
import io.github.slaxnetwork.routing.game.gameRouting
import io.github.slaxnetwork.routing.news.newsRouting
import io.github.slaxnetwork.routing.profile.profileRouting
import io.github.slaxnetwork.routing.ranks.ranksRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)

    install(StatusPages) {
        status(HttpStatusCode.TooManyRequests) { _, _ -> throw RouteError.RateLimited }
        status(HttpStatusCode.Unauthorized) { _, _ -> throw RouteError.Unauthorized }

        exception<RouteError> { call, cause ->
            val statusCode = HttpStatusCode.fromValue(cause.statusCode).run {
                if(this.description.equals("unknown status code", true)) {
                    call.response.status() ?: HttpStatusCode.BadRequest
                }
                this
            }

            call.respond(statusCode, cause)
        }

        // TODO: 1/1/2023 fix up
        exception<Throwable> { call, cause ->
            cause.printStackTrace()

            call.respond(
                HttpStatusCode.InternalServerError,
                RouteError(500, cause.message ?: "no message provided.")
            )
        }
    }

    routing {
        profileRouting()
        gameRouting()
        ranksRouting()
        newsRouting()
    }
}
