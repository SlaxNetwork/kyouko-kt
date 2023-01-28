package io.github.slaxnetwork.database.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.dto.ProfilePreferences
import kotlinx.serialization.Serializable

@Serializable
data class ProfilePreferencesModel(
    val language: String
) {
    companion object {
        fun fromRowData(
            rowData: RowData
        ) = ProfilePreferencesModel(
            rowData.getString("language") ?: throw DatabaseDeserializeException(ProfilePreferences::language)
        )
    }

    fun toDTO() = ProfilePreferences(
        language
    )
}
