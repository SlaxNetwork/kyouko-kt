package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.shared.JSON
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(JSON)
    }
}