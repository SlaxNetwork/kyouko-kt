package io.github.slaxnetwork.api.utils

import io.github.slaxnetwork.shared.JSON
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
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
        host = System.getenv("API_HOST") ?: "0.0.0.0"
        port = runCatching {
            System.getenv("API_PORT").toInt()
        }.getOrNull() ?: 8080

        url {
            protocol = URLProtocol.HTTP
        }

        bearerAuth("kyouko")

        contentType(ContentType.Application.Json)
    }
}