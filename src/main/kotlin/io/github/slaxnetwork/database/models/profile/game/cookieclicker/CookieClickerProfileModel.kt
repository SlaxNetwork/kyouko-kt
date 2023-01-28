package io.github.slaxnetwork.database.models.profile.game.cookieclicker

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.dto.game.cookieclicker.CookieClickerProfile

data class CookieClickerProfileModel(
    val id: Int,

    val cookies: Int,

    val upgrades: CookieClickerUpgradesModel
) {
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::id),
                rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::cookies),
                CookieClickerUpgradesModel(rowData)
            )

    fun toView() = CookieClickerProfile(
        cookies,
        upgrades.toView()
    )
}