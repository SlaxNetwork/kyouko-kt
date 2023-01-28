package io.github.slaxnetwork.api.models.profile

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.ProfilePreferencesView
import io.github.slaxnetwork.api.models.views.profile.ProfileView
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Profile(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String,

    @SerialName("game_profile_id")
    val gameProfileId: Int,

    @SerialName("preferences_id")
    val preferencesId: Int
) {
    companion object {
        fun fromRowData(
            rowData: RowData
        ) = Profile(
            rowData.getAs("id") ?: throw DatabaseDeserializeException(Profile::id),
            rowData.getString("rankId") ?: throw DatabaseDeserializeException(Profile::rankId),
            rowData.getInt("gameProfileId") ?: throw DatabaseDeserializeException(Profile::gameProfileId),
            rowData.getInt("preferencesId") ?: throw DatabaseDeserializeException(Profile::preferencesId)
        )
    }

    fun toView(
        preferencesView: ProfilePreferencesView
    ) = ProfileView(
        id,
        preferencesView,
        rankId
    )
}