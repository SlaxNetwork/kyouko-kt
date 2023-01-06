package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.utils.requestPrincipal
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Application.configureRateLimiter() {
    install(RateLimit) {
        global {
            requestKey { call ->
                call.requestPrincipal ?: PrincipalResult.Unauthorized
            }

            rateLimiter { _, key ->
                if(key is PrincipalResult.Authorized) {
                    RateLimiter.Unlimited
                } else {
                    RateLimiter.default(30, 30.toDuration(DurationUnit.SECONDS))
                }
            }
        }
    }
}