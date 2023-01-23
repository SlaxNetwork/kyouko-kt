package io.github.slaxnetwork.database.impl.postgres.repositories.game

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.profile.game.cookieclicker.CookieClickerProfile
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import java.util.*

class PostgresCookieClickerRepository(
    private val conn: SuspendingConnection
) : CookieClickerRepository {
    override suspend fun create(uuid: UUID): Int {
        val upgradeId = conn.execute(
            """
                INSERT INTO "CookieClickerProfileUpgrades" DEFAULT VALUES RETURNING (id);
            """.trimIndent()
        ).firstRow.getInt("id")!!

        // TODO: 1/21/2023 clean this up so it automatically sets default values besides what i want.
        val id = conn.execute(
            """
                INSERT INTO "CookieClickerProfile" (id, cookies, "cookieClickerProfileUpgradesId") VALUES (default, default, ?) RETURNING (id);
            """.trimIndent(),
            upgradeId
        ).firstRow.getInt("id")!!

        conn.execute(
            """
                UPDATE "GameProfile" GP SET "cookieClickerProfileId" = ?
                    WHERE GP.id = (SELECT (P."gameProfileId") FROM "Profile" as P WHERE id = ?);
            """.trimIndent(),
            id, uuid
        )

        return id
    }

    override suspend fun findById(id: Int): CookieClickerProfile? {
        val row = conn.execute(
            """
                SELECT * FROM "CookieClickerProfile" CCP
                    INNER JOIN "CookieClickerProfileUpgrades" CCPU ON CCPU.id = CCP."cookieClickerProfileUpgradesId"
                    AND CCP.id = ?;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return CookieClickerProfile(row)
    }

    override suspend fun findByUUID(uuid: UUID): CookieClickerProfile? {
        val id = conn.execute(
            """
                SELECT (GP."cookieClickerProfileId") FROM "Profile" P
                    INNER JOIN "GameProfile" GP ON GP.id = P."gameProfileId"
                    WHERE P.id = ? LIMIT 1;
            """.trimIndent(),
            uuid
        ).firstNullableRow?.getInt("cookieClickerProfileId") ?: return null

        return findById(id)
    }
}