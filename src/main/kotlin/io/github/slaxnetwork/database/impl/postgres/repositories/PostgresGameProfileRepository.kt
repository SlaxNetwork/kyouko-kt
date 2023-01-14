package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.profile.game.GameProfile
import io.github.slaxnetwork.api.models.views.profile.game.GameProfileView
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.models.NetworkGames
import java.util.*

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

    override suspend fun findByIdAndPopulate(id: Int, type: String): GameProfile? {
        TODO("Not yet implemented")
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

    override suspend fun findByUUIDAndPopulate(uuid: UUID, type: String): GameProfileView? {
        // invalid game passed.
        val networkGame = NetworkGames.getById(type)
            ?: return null

        val gameProfile = findByUUID(uuid)
            ?: return null

        // get game profile id of requested game type on the game profile.
        val gameProfileId = networkGame.gameProfileIdAccessor.get(gameProfile)
            ?: return null

        val properTableName = "\"${networkGame.tableName}\""

        val row = conn.execute(
                "SELECT * FROM ${properTableName} WHERE id = ? LIMIT 1;",
            gameProfileId
        ).firstNullableRow ?: return null

        return GameProfileView.populated(
            networkGame.associatedGameProfile,
            row
        )
    }
}