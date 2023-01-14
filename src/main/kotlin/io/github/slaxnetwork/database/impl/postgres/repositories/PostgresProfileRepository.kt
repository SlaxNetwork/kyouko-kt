package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.dto.ProfileDTO
import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import org.litote.kmongo.Id
import java.util.*

class PostgresProfileRepository(
    private val conn: SuspendingConnection,
    private val gameProfileRepository: GameProfileRepository
) : ProfileRepository {
    override suspend fun create(profile: Profile): ProfileDTO {
        val emptyGameProfileId = gameProfileRepository.create()

        val result = conn.execute("""
            INSERT INTO "Profile" (id, "gameProfileId") VALUES (?, ?) RETURNING *;
        """.trimIndent(), profile.uuid, emptyGameProfileId)

        return ProfileDTO(result.rows.firstOrNull() ?: throw RuntimeException("todo change pls ty future me"))
    }

    override suspend fun findByName(name: String): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun findByUUID(uuid: UUID): ProfileDTO? {
        val result = conn.execute("""
            SELECT * FROM "Profile" WHERE id = ? LIMIT 1;
        """.trimIndent(), uuid)

        return ProfileDTO(
            result.rows.firstOrNull()
                ?: return null
        )
    }

    override suspend fun addPunishment(uuid: UUID, id: Id<Punishment>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<ProfileDTO> {
        return conn.execute("""
            SELECT * FROM "Profile";
        """.trimIndent()).rows.map { ProfileDTO(it) }
    }
}