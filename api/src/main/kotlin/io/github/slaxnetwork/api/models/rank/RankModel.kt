package io.github.slaxnetwork.api.models.rank

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class RankModel(
    @Contextual
    val id: String
) {
    constructor(rowData: RowData) :
            this(
                rowData.getString("id") ?: throw DatabaseDeserializeException(RankModel::id)
            )

    companion object {
        const val DEFAULT_RANK_ID = "default"
    }
}