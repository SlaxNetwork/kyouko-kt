package io.github.slaxnetwork.database.repositories.game

import io.github.slaxnetwork.api.models.views.profile.game.PopulatedGameProfileView
import java.util.UUID

interface GameRepository <T : PopulatedGameProfileView> {
    suspend fun getPopulatedGameProfileById(id: Int): T?

    suspend fun getPopulatedGameProfileByUUID(uuid: UUID): T?
}