package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.models.profile.game.GameProfileModel
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository
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

    override suspend fun getGameProfileId(uuid: UUID): Int? {
        return conn.execute(
            """
                SELECT (GP.id) FROM "Profile"
                    INNER JOIN "GameProfile" GP on GP.id = "Profile"."gameProfileId"
                    AND "Profile"."id" = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow?.getInt("id")
    }

    override suspend fun findById(id: Int): GameProfileModel? {
        val row = conn.execute(
            """
                SELECT * FROM "GameProfile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return GameProfileModel.fromRowData(row)
    }

    override suspend fun findByUUID(uuid: UUID): GameProfileModel? {
        val row = conn.execute(
            """
                SELECT (GP.*) FROM "Profile"
                    INNER JOIN "GameProfile" GP on GP.id = "Profile"."gameProfileId"
                    AND "Profile"."id" = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow ?: return null

        return GameProfileModel.fromRowData(row)
    }

    override suspend fun setCookieClickerProfileId(uuid: UUID, id: Int) {
        val gameProfileId = getGameProfileId(uuid) ?: return
        conn.execute(
            """UPDATE "GameProfile" SET "cookieClickerProfileId" = ? WHERE id = ?;""",
            id, gameProfileId
        )
    }
}