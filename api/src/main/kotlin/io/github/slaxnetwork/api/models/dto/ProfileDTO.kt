package io.github.slaxnetwork.api.models.dto

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DTOException
import kotlinx.serialization.Contextual
import java.util.UUID

@kotlinx.serialization.Serializable
data class ProfileDTO(
    @Contextual
    val id: UUID,
    val rankId: String,
    val gameProfileId: Int

) {
    constructor(rowData: RowData) :
            this(
                rowData.getAs("id") ?: throw DTOException("id"),
                rowData.getString("rankId") ?: throw DTOException("rankId"),
                rowData.getInt("gameProfileId") ?: throw DTOException("gameProfileId")
            )
}
