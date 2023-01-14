package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.shared.JSON
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(JSON)
    }
}