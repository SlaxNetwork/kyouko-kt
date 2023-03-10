package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.models.profile.ProfileModel
import io.github.slaxnetwork.database.models.profile.game.GameProfileModel
import io.github.slaxnetwork.database.models.rank.RankModel
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.ProfilePreferencesRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import java.util.*

class PostgresProfileRepository(
    private val conn: SuspendingConnection,
    private val gameProfileRepository: GameProfileRepository,
    private val profilePreferencesRepository: ProfilePreferencesRepository
) : ProfileRepository {
    override suspend fun create(uuid: UUID): ProfileModel {
        val preferencesId = profilePreferencesRepository.create()
        val gameProfileId = gameProfileRepository.create()

        val row = conn.execute(
            """
                INSERT INTO "Profile" VALUES (?, ?, ?, ?) RETURNING *;
            """.trimIndent(),
            uuid, RankModel.DEFAULT_RANK_ID, gameProfileId, preferencesId
        ).firstRow

        return ProfileModel.fromRowData(row)
    }

    override suspend fun findByName(name: String): ProfileModel? {
        TODO("Not yet implemented")
    }

    override suspend fun findByUUID(uuid: UUID): ProfileModel? {
        val row = conn.execute(
            """
                SELECT * FROM "Profile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow ?: return null

        return ProfileModel.fromRowData(row)
    }

    override suspend fun getGameProfile(uuid: UUID): GameProfileModel? {
        val gameProfileId = conn.execute(
            """
                SELECT ("gameProfileId") from "Profile" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow?.getInt("gameProfileId") ?: return null

        return gameProfileRepository.findById(gameProfileId)
    }

    override suspend fun getAll(): List<ProfileModel> {
        return conn.execute("""
            SELECT * FROM "Profile";
        """.trimIndent()).rows.map { ProfileModel.fromRowData(it) }
    }
}