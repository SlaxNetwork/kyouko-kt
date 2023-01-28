package io.github.slaxnetwork.api.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.ProfilePreferencesView
import kotlinx.serialization.Serializable

@Serializable
data class ProfilePreferences(
    val language: String
) {
    companion object {
        fun fromRowData(
            rowData: RowData
        ) = ProfilePreferences(
            rowData.getString("language") ?: throw DatabaseDeserializeException(ProfilePreferences::language)
        )
    }

    fun toView() = ProfilePreferencesView(
        language
    )
}
