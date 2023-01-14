package io.github.slaxnetwork.api.models.rank

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rank(
    @Contextual
    val id: String
) {
    constructor(rowData: RowData) :
            this(
                rowData.getString("id") ?: throw DatabaseDeserializeException(Rank::id)
            )

    companion object {
        const val DEFAULT_RANK_ID = "default"
    }
}