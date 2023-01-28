package io.github.slaxnetwork.api.models.profile.game

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GameProfileModel(
    val id: Int,

    @SerialName("cookie_clicker_profile_id")
    val cookieClickerProfileId: Int? = null
) {
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(GameProfileModel::id),
                rowData.getInt("cookieClickerProfileId")
            )
}