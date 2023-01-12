package io.github.slaxnetwork.database.impl.postgres

import com.github.jasync.sql.db.*
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import java.util.concurrent.TimeUnit

object PostgresDatabase {
    fun create(): SuspendingConnection {
        val conn: SuspendingConnection = PostgreSQLConnectionBuilder
            .createConnectionPool {
                username = "root"
                password = "password"
                host = "127.0.0.1"
                port = 5432
                database = "slax"
                currentSchema = "public"

                maxIdleTime = TimeUnit.MINUTES.toMillis(15)
                connectionValidationInterval = TimeUnit.SECONDS.toMillis(30)
                maxPendingQueries = 10_000
            }.asSuspending

        return conn
    }
}