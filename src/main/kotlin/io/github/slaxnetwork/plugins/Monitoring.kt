package io.github.slaxnetwork.plugins

import com.codahale.metrics.Slf4jReporter
import io.ktor.server.application.*
import io.ktor.server.metrics.dropwizard.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level
import java.util.concurrent.TimeUnit

fun Application.configureMonitoring() {
    // debugging.
    val outputLoggingGarbage = false

    if(outputLoggingGarbage) {
        install(DropwizardMetrics) {
            Slf4jReporter.forRegistry(registry)
                .outputTo(this@configureMonitoring.log)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build()
                .start(10, TimeUnit.SECONDS)
        }
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}
