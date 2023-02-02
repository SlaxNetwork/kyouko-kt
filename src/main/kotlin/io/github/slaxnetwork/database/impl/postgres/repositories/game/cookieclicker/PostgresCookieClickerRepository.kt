package io.github.slaxnetwork.database.impl.postgres.repositories.game.cookieclicker

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.database.models.profile.game.cookieclicker.CookieClickerProfileModel
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerUpgradesRepository
import java.util.*

class PostgresCookieClickerRepository(
    private val conn: SuspendingConnection,
    private val gameProfileRepo: GameProfileRepository,
    private val upgradesRepo: CookieClickerUpgradesRepository
) : CookieClickerRepository {
    override suspend fun create(uuid: UUID): Int {
        val upgradesId = upgradesRepo.create()

        // TODO: 1/21/2023 clean this up so it automatically sets default values besides what i want.
        val id = conn.execute(
            """
                INSERT INTO "CookieClickerProfile" (id, cookies, "cookieClickerProfileUpgradesId") VALUES (default, default, ?) RETURNING (id);
            """.trimIndent(),
            upgradesId
        ).firstRow.getInt("id")!!

        gameProfileRepo.setCookieClickerProfileId(uuid, id)
//
        return id
    }

    override suspend fun findById(id: Int): CookieClickerProfileModel? {
        val row = conn.execute(
            """
                SELECT * FROM "CookieClickerProfile" CCP
                    INNER JOIN "CookieClickerProfileUpgrades" CCPU ON CCPU.id = CCP."cookieClickerProfileUpgradesId"
                    AND CCP.id = ?;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return CookieClickerProfileModel.fromRowData(row)
    }

    override suspend fun findByUUID(uuid: UUID): CookieClickerProfileModel? {
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