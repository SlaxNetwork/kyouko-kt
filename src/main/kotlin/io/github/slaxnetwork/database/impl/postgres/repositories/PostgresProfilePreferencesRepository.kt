package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.profile.ProfilePreferences
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.ProfilePreferencesRepository

class PostgresProfilePreferencesRepository(
    private val conn: SuspendingConnection
) : ProfilePreferencesRepository {
    override suspend fun create(): Int {
        return conn.execute(
            """
                INSERT INTO "ProfilePreferences" DEFAULT VALUES RETURNING ("id");
            """.trimIndent()
        ).firstRow.getInt("id")!!
    }

    override suspend fun findById(id: Int): ProfilePreferences? {
        val row = conn.execute(
            """
                SELECT * FROM "ProfilePreferences" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return ProfilePreferences.fromRowData(row)
    }
}