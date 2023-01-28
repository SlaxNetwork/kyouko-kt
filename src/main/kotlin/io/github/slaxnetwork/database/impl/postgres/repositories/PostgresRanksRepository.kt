package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.rank.RankModel
import io.github.slaxnetwork.database.impl.postgres.utils.execute
import io.github.slaxnetwork.database.impl.postgres.utils.firstNullableRow
import io.github.slaxnetwork.database.impl.postgres.utils.firstRow
import io.github.slaxnetwork.database.repositories.RanksRepository

class PostgresRanksRepository(
    private val conn: SuspendingConnection
) : RanksRepository {
    override suspend fun findById(id: String): RankModel? {
        val row = conn.execute(
            """
                SELECT * FROM "Rank" WHERE id = ? LIMIT 1;
            """.trimIndent(),
            id
        ).firstNullableRow ?: return null

        return RankModel(row)
    }

    override suspend fun create(rank: RankModel): RankModel {
        val row = conn.execute(
            """
                INSERT INTO "Rank" (id, name) VALUES (?, ?) RETURNING *;
            """.trimIndent(),
            rank.id, rank.id
        ).firstRow

        return RankModel(row)
    }

    override suspend fun update(rankId: String, rank: RankModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<RankModel> {
        return conn.execute(
            """
                SELECT * FROM "Rank";
            """.trimIndent()
        ).rows.map { RankModel(it) }
    }
}