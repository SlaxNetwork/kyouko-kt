package io.github.slaxnetwork.database.impl.postgres

import com.github.jasync.sql.db.SuspendingConnection
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import io.github.slaxnetwork.Environment
import java.util.concurrent.TimeUnit

object PostgresDatabase {
    fun create(env: Environment): SuspendingConnection {
        val conn: SuspendingConnection = PostgreSQLConnectionBuilder
            .createConnectionPool(env.dbUrl) {
                currentSchema = "public"
                maxIdleTime = TimeUnit.MINUTES.toMillis(15)
                connectionValidationInterval = TimeUnit.SECONDS.toMillis(30)
                maxPendingQueries = 10_000
            }.asSuspending

        return conn
    }
}