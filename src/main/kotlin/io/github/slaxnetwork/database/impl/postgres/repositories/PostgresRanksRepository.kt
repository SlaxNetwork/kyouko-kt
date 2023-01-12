package io.github.slaxnetwork.database.impl.postgres.repositories

import com.github.jasync.sql.db.ResultSet
import com.github.jasync.sql.db.RowData
import com.github.jasync.sql.db.SuspendingConnection
import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.database.repositories.RanksRepository
import io.github.slaxnetwork.database.impl.postgres.utils.execute

class PostgresRanksRepository(
    private val conn: SuspendingConnection
) : RanksRepository {
    override suspend fun findById(id: String): Rank? {
        fun fromRowData(rowData: RowData): Rank = Rank(
            rowData.getString("id")!!
        )

        val result = conn.execute("""
            SELECT * FROM "Rank" WHERE id = ? LIMIT 1;
        """.trimIndent(), id)

        return fromRowData(
            result.rows.firstOrNull()
                ?: return null
        )
    }

    override suspend fun create(rank: Rank): Rank {
        conn.execute("""
            INSERT INTO "Rank" (id, name) VALUES (?, ?)
        """.trimIndent(), rank.id, rank.id)

        return rank
    }

    override suspend fun update(rankId: String, rank: Rank) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Rank> {
        fun fromResultSet(rows: ResultSet): List<Rank> {
            return rows.mapNotNull {
                Rank(it.getString("id")!!)
            }
        }

        val result = conn.execute("""
            SELECT * FROM "Rank"
        """.trimIndent())

        return fromResultSet(result.rows)
    }
}