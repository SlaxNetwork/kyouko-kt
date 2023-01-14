package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.tmp.GameProfile
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
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
        ).firstRow.getInt("id")!!
    }

    override suspend fun find(id: Int): GameProfile? {
        val row = conn.execute(
            """
                SELECT * FROM "GameProfile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return GameProfile(row)
    }
}