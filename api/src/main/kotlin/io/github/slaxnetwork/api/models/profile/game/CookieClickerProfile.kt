package io.github.slaxnetwork.api.models.profile.game

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.annotations.RowDataConstructor
import io.github.slaxnetwork.api.annotations.ViewSerializerMethod
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.game.CookieClickerProfileView
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfile(
    val id: Int,

    val cookies: Int
) {
    @RowDataConstructor
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfile::id),
                rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfile::cookies),
            )

    @ViewSerializerMethod
    fun toView() = CookieClickerProfileView(
        cookies
    )
}