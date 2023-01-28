package io.github.slaxnetwork.api.models.profile.game.cookieclicker

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.exceptions.DatabaseDeserializeException
import io.github.slaxnetwork.api.models.views.profile.game.cookieclicker.CookieClickerUpgrades

data class CookieClickerUpgradesModel(
    val testUpgrade: Int
) {
    constructor(rowData: RowData) :
            this(
                rowData.getInt("testUpgrade") ?: throw DatabaseDeserializeException(CookieClickerUpgradesModel::testUpgrade)
            )

    fun toView() = CookieClickerUpgrades(
        testUpgrade
    )
}