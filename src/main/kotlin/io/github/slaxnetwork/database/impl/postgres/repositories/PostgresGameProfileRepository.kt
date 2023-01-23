package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.profile.game.GameProfile
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import java.util.*

class PostgresGameProfileRepository(
    private val conn: SuspendingConnection
): GameProfileRepository {
    override suspend fun create(): Int {
        return conn.execute(
            """
                INSERT INTO "GameProfile" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        ).firstRow.getInt("id")!!
    }

    override suspend fun findById(id: Int): GameProfile? {
        val row = conn.execute(
            """
                SELECT * FROM "GameProfile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return GameProfile(row)
    }

    override suspend fun findByUUID(uuid: UUID): GameProfile? {
        val row = conn.execute(
            """
                SELECT (GP.*) FROM "Profile"
                    INNER JOIN "GameProfile" GP on GP.id = "Profile"."gameProfileId"
                    AND "Profile"."id" = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow ?: return null

        return GameProfile(row)
    }
}