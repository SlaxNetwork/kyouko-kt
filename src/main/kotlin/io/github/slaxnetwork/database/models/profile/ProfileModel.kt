package io.github.slaxnetwork.database.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.dto.Profile
import io.github.slaxnetwork.api.dto.ProfilePreferences
import kotlinx.serialization.Contextual
import java.util.*

data class ProfileModel(
    @Contextual
    val id: UUID,

    val rankId: String,

    val gameProfileId: Int,

    val preferencesId: Int
) {
    companion object {
        fun fromRowData(
            rowData: RowData
        ) = ProfileModel(
            rowData.getAs("id") ?: throw DatabaseDeserializeException(ProfileModel::id),
            rowData.getString("rankId") ?: throw DatabaseDeserializeException(ProfileModel::rankId),
            rowData.getInt("gameProfileId") ?: throw DatabaseDeserializeException(ProfileModel::gameProfileId),
            rowData.getInt("preferencesId") ?: throw DatabaseDeserializeException(ProfileModel::preferencesId)
        )
    }

    fun toView(
        preferences: ProfilePreferences
    ) = Profile(
        id,
        preferences,
        rankId
    )
}