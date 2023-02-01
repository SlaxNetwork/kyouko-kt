package io.github.slaxnetwork

import ch.qos.logback.classic.LoggerContext
import io.github.cdimascio.dotenv.Dotenv
import io.github.slaxnetwork.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromStringMap
import org.slf4j.LoggerFactory

fun main() {
    val env = Environment.loadEnvironment()

    embeddedServer(
        Netty,
        port = env.port,
        host = "0.0.0.0"
    ) {
        configureKoin(env)
        configureSerialization()
        configureSockets()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
        configureRateLimiter()
        configureRouting()

        // hacky way to make mongo only display errors.
        (LoggerFactory.getILoggerFactory() as LoggerContext).getLogger("org.mongodb.driver").level = ch.qos.logback.classic.Level.ERROR
    }.start(wait = true)
}