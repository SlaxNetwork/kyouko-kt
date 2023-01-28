package io.github.slaxnetwork.api.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.Profile
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import java.util.*

data class ProfileModel(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String,

    val gameProfileId: Int
) {
    constructor(rowData: RowData) :
            this(
                rowData.getAs("id") ?: throw DatabaseDeserializeException(ProfileModel::id),
                rowData.getString("rankId") ?: throw DatabaseDeserializeException(ProfileModel::rankId),
                rowData.getInt("gameProfileId") ?: throw DatabaseDeserializeException(ProfileModel::gameProfileId)
            )

    fun toView() = Profile(
        id,
        rankId
    )
}