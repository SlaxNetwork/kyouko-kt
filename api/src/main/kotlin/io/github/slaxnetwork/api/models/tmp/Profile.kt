package io.github.slaxnetwork.api.models.tmp

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DTOException
import io.github.slaxnetwork.api.models.views.CookieClickerProfileView
import io.github.slaxnetwork.api.models.views.ProfileView
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID
import kotlin.reflect.full.memberProperties

@Serializable
data class Profile(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String,

    @SerialName("game_profile_id")
    val gameProfileId: Int
) {
    constructor(rowData: RowData) :
            this(
                rowData.getAs("uuid") ?: throw DTOException("uuid"),
                rowData.getString("rankId") ?: throw DTOException("rankId"),
                rowData.getInt("gameProfileId") ?: throw DTOException("gameProfileId")
            )

    fun toView(
        gameProfile: GameProfile,
        cookieClickerProfile: CookieClickerProfile
    ) = ProfileView(
        id,
        rankId,
        gameProfile.toView(cookieClickerProfile)
    )
}