package io.github.slaxnetwork.api.models.profile.game

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.annotations.RowDataConstructor
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameProfile(
    val id: Int,

    @SerialName("cookie_clicker_profile_id")
    val cookieClickerProfileId: Int? = null
) {
    @RowDataConstructor
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(GameProfile::id),
                rowData.getInt("cookieClickerProfileId")
            )
}