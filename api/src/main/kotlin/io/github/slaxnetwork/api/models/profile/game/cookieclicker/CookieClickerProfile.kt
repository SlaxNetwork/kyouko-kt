package io.github.slaxnetwork.api.models.profile.game.cookieclicker

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.game.cookieclicker.CookieClickerProfileView
import kotlinx.serialization.Serializable

@Serializable
data class CookieClickerProfile(
    val id: Int,

    val cookies: Int,

    val upgrades: CookieClickerUpgrades
) {
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfile::id),
                rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfile::cookies),
                CookieClickerUpgrades(rowData)
            )

    fun toView() = CookieClickerProfileView(
        cookies,
        upgrades.toView()
    )
}