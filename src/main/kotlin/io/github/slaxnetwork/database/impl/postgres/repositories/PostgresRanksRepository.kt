package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.RanksRepository

class PostgresRanksRepository(
    private val conn: SuspendingConnection
) : RanksRepository {
    override suspend fun findById(id: String): Rank? {
        val row = conn.execute(
            """
                SELECT * FROM "Rank" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return Rank(row)
    }

    override suspend fun create(rank: Rank): Rank {
        val row = conn.execute(
            """
                INSERT INTO "Rank" (id, name) VALUES (?, ?) RETURNING *;
            """.trimIndent(),
            rank.id, rank.id
        ).firstRow

        return Rank(row)
    }

    override suspend fun update(rankId: String, rank: Rank) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Rank> {
        return conn.execute(
            """
                SELECT * FROM "Rank";
            """.trimIndent()
        ).rows.map { Rank(it) }
    }
}