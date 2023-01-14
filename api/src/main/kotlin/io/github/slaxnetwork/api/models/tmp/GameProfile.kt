package io.github.slaxnetwork.api.models.tmp

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DTOException
import io.github.slaxnetwork.api.models.views.GameProfileView
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameProfile(
    val id: Int,

    @SerialName("cookie_clicker_profile_id")
    val cookieClickerProfileId: Int? = null
) {
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DTOException("id"),
                rowData.getInt("cookieClickerProfileId")
            )

    fun toView(
        cookieClickerProfile: CookieClickerProfile
    ) = GameProfileView(
        cookieClickerProfile.toView()
    )
}