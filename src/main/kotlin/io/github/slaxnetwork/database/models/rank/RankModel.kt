package io.github.slaxnetwork.database.models.rank

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.dto.rank.Rank
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

data class RankModel(
    @Contextual
    val id: String
) {
    constructor(rowData: RowData) :
            this(
                rowData.getString("id") ?: throw DatabaseDeserializeException(RankModel::id)
            )

    fun toDTO() = Rank(
        id
    )

    companion object {
        const val DEFAULT_RANK_ID = "default"
    }
}