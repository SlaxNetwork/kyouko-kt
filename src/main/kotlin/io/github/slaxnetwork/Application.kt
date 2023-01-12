package io.github.slaxnetwork

import ch.qos.logback.classic.LoggerContext
import io.github.slaxnetwork.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureSockets()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureRateLimiter()
    configureRouting()

    // hacky way to make mongo only display errors.
    (LoggerFactory.getILoggerFactory() as LoggerContext).getLogger("org.mongodb.driver").level = ch.qos.logback.classic.Level.ERROR
}
