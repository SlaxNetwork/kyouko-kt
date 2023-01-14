package io.github.slaxnetwork.database.repositories.game

interface CookieClickerRepository {
    suspend fun create(): Int
}