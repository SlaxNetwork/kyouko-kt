package io.github.slaxnetwork.database.repositories

interface GameProfileRepository {
    suspend fun create(): Int
}