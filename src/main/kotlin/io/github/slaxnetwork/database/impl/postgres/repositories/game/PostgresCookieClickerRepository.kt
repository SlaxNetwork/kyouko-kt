package io.github.slaxnetwork.database.impl.postgres.repositories.game

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import java.util.*

class PostgresCookieClickerRepository(
    private val conn: SuspendingConnection
) : CookieClickerRepository {
    override suspend fun create(uuid: UUID): Int {
        val id = conn.execute(
            """
                INSERT INTO "CookieClickerProfile" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        ).firstRow.getInt("id")!!

        conn.execute(
            """
                UPDATE "GameProfile" as GP SET "cookieClickerProfileId" = ?
                    WHERE GP.id = (SELECT (P."gameProfileId") FROM "Profile" as P WHERE id = ?);
            """.trimIndent(),
            id, uuid
        )

        return id
    }
}