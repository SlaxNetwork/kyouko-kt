package io.github.slaxnetwork.database.impl.postgres

import com.github.jasync.sql.db.SuspendingConnection
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import io.ktor.server.application.*
import java.util.concurrent.TimeUnit

object PostgresDatabase {
    fun create(env: ApplicationEnvironment): SuspendingConnection {
        val conf = env.config
        val type = if(env.developmentMode) "dev" else "prod"

        val conn: SuspendingConnection = PostgreSQLConnectionBuilder
            .createConnectionPool {
                username = conf.property("${type}.database.username").getString()
                password = conf.property("${type}.database.password").getString()
                host = conf.property("${type}.database.host").getString()
                port = conf.property("${type}.database.port").getString().toInt()
                database = conf.property("${type}.database.name").getString()

                currentSchema = "public"
                maxIdleTime = TimeUnit.MINUTES.toMillis(15)
                connectionValidationInterval = TimeUnit.SECONDS.toMillis(30)
                maxPendingQueries = 10_000
            }.asSuspending

        return conn
    }
}