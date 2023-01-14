package io.github.slaxnetwork.database.impl.postgres.repositories.game

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository

class PostgresCookieClickerRepository(
    private val conn: SuspendingConnection
) : CookieClickerRepository {
    override suspend fun create(): Int {
        return conn.execute(
            """
                INSERT INTO "CookieClickerProfile" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        )
            .firstRow
            .getInt("id")!!
    }
}