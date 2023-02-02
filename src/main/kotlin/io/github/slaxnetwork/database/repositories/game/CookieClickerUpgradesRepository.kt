package io.github.slaxnetwork.database.repositories.game

interface CookieClickerUpgradesRepository {
    suspend fun create(): Int
}