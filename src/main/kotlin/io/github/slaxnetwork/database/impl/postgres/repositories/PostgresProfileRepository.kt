package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.api.models.profile.game.GameProfile
import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import java.util.*

class PostgresProfileRepository(
    private val conn: SuspendingConnection,
    private val gameProfileRepository: GameProfileRepository
) : ProfileRepository {
    override suspend fun create(uuid: UUID): Profile {
        val gameProfileId = gameProfileRepository.create()

        val row = conn.execute(
            """
                INSERT INTO "Profile" (id, "rankId", "gameProfileId") VALUES (?, ?, ?) RETURNING *; 
            """.trimIndent(),
            uuid, Rank.DEFAULT_RANK_ID, gameProfileId
        ).firstRow

        return Profile(row)
    }

    override suspend fun findByName(name: String): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun findByUUID(uuid: UUID): Profile? {
        val row = conn.execute(
            """
                SELECT * FROM "Profile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow ?: return null

        return Profile(row)
    }

    override suspend fun getGameProfile(uuid: UUID): GameProfile? {
        val gameProfileId = conn.execute(
            """
                SELECT ("gameProfileId") from "Profile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow?.getInt("gameProfileId") ?: return null

        return gameProfileRepository.find(gameProfileId)
    }

    override suspend fun getAll(): List<Profile> {
        return conn.execute("""
            SELECT * FROM "Profile";
        """.trimIndent()).rows.map { Profile(it) }
    }
}