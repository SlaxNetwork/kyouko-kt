package io.github.slaxnetwork.database.impl.postgres.repositories.game.cookieclicker

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.game.CookieClickerUpgradesRepository

class PostgresCookieClickerUpgradesRepository(
    private val conn: SuspendingConnection
) : CookieClickerUpgradesRepository {
    override suspend fun create(): Int {
        return conn.execute(
            """
                INSERT INTO "CookieClickerProfileUpgrades" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        ).firstRow.getInt("id")!!
    }
}