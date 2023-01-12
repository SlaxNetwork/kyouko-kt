package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.profile.Profile
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import org.litote.kmongo.Id
import java.util.*

class PostgresProfileRepository(
    private val conn: SuspendingConnection
) : ProfileRepository {
    override suspend fun create(profile: Profile): Profile {
        conn.execute("""
            INSERT INTO "Profile" (id, "rankId", "gameProfileId") VALUES (?, ?, ?)
        """.trimIndent(), profile.uuid, "default", 1)

        return profile
    }

    override suspend fun findByName(name: String): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun findByUUID(uuid: UUID): Profile? {
        val result = conn.execute("""
            SELECT * FROM "Profile" WHERE id = ? LIMIT 1;
        """.trimIndent(), uuid)

        val rowData = result.rows.first()
        return Profile(
            uuid,
            rowData.getString("username")!!
        )
    }

    override suspend fun addPunishment(uuid: UUID, id: Id<Punishment>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Profile> {
        TODO("Not yet implemented")
    }
}