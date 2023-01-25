package io.github.slaxnetwork.api.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.ProfileView
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import java.util.*

@kotlinx.serialization.Serializable
data class Profile(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String,

    val gameProfileId: Int
) {
    constructor(rowData: RowData) :
            this(
                rowData.getAs("id") ?: throw DatabaseDeserializeException(Profile::id),
                rowData.getString("rankId") ?: throw DatabaseDeserializeException(Profile::rankId),
                rowData.getInt("gameProfileId") ?: throw DatabaseDeserializeException(Profile::gameProfileId)
            )

    fun toView() = ProfileView(
        id,
        rankId
    )
}