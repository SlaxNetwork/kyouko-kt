package io.github.slaxnetwork.database.repositories.game

import java.util.UUID

interface CookieClickerRepository {
    suspend fun create(uuid: UUID): Int
}