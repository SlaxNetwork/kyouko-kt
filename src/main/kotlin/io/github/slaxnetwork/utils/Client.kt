package io.github.slaxnetwork.utils

import io.github.slaxnetwork.shared.JSON
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

internal val client = HttpClient(CIO) {
    install(HttpRequestRetry) {
        maxRetries = 3
        retryIf { request, response ->
            !response.status.isSuccess()
        }
        delayMillis {
            // 0.5s per retry because this takes too long to fail.
            500L
        }
        modifyRequest { request ->
            request.headers.append("x-retry-count", retryCount.toString())
        }
    }

    install(ContentNegotiation) {
        json(JSON)
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
        }

        contentType(ContentType.Application.Json)
    }
}