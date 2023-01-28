package io.github.slaxnetwork.database.models.profile.game.cookieclicker

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.dto.game.cookieclicker.CookieClickerProfile

data class CookieClickerProfileModel(
    val id: Int,

    val cookies: Int,

    val upgrades: CookieClickerUpgradesModel
) {
    @Deprecated("use static method instead", level = DeprecationLevel.WARNING)
    constructor(rowData: RowData) :
            this(
                rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::id),
                rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::cookies),
                CookieClickerUpgradesModel(rowData)
            )

    companion object {
        fun fromRowData(rowData: RowData) = CookieClickerProfileModel(
            rowData.getInt("id") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::id),
            rowData.getInt("cookies") ?: throw DatabaseDeserializeException(CookieClickerProfileModel::cookies),
            CookieClickerUpgradesModel.fromRowData(rowData)
        )
    }

    fun toDTO() = CookieClickerProfile(
        cookies,
        upgrades.toDTO()
    )
}