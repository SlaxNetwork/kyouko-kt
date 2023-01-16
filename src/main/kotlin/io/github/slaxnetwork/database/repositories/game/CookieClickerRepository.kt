package io.github.slaxnetwork.database.repositories.game

import io.github.slaxnetwork.api.models.views.profile.game.cookieclicker.CookieClickerProfileView
import java.util.UUID

interface CookieClickerRepository : GameRepository<CookieClickerProfileView> {
    suspend fun create(uuid: UUID): Int
}