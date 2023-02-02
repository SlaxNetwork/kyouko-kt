package io.github.slaxnetwork.database.models.profile.game.cookieclicker

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.dto.profile.game.cookieclicker.CookieClickerProfile

data class CookieClickerProfileModel(
    val id: Int,

    val cookies: Int,

    val cookieClickerUpgradesId: Int
) {
    companion object {
        fun fromRowData(rowData: RowData) = CookieClickerProfileModel(
            rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::id),
            rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::cookies),
            rowData.getInt("cookieClickerProfileUpgradesId") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::cookieClickerUpgradesId)
        )
    }

    fun toDTO(
        upgrades: CookieClickerUpgradesModel
    ) = CookieClickerProfile(
        cookies,
        upgrades.toDTO()
    )
}