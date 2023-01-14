package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository

class PostgresGameProfileRepository(
    private val conn: SuspendingConnection
) : GameProfileRepository {
    override suspend fun create(): Int {
        return conn.execute(
            """
                INSERT INTO "GameProfile" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        )
            .firstRow
            .getInt("id")!!
    }
}