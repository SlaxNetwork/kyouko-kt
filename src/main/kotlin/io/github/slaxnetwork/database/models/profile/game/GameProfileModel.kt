package io.github.slaxnetwork.database.models.profile.game

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GameProfileModel(
    val id: Int,

    @SerialName("cookie_clicker_profile_id")
    val cookieClickerProfileId: Int? = null
) {
    @Deprecated("use static method instead", level = DeprecationLevel.WARNING)
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(GameProfileModel::id),
                rowData.getInt("cookieClickerProfileId")
            )

    companion object {
        fun fromRowData(rowData: RowData) = GameProfileModel(
            rowData.getInt("id") ?: throw DatabaseDeserializeException(GameProfileModel::id),
            rowData.getInt("cookieClickerProfileId")
        )
    }

    fun toDTO() {
        TODO("implement pls")
    }
}