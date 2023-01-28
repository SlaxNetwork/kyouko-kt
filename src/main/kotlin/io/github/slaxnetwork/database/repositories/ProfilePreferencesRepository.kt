package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.api.models.profile.ProfilePreferences

interface ProfilePreferencesRepository {
    suspend fun create(): Int

    suspend fun findById(id: Int): ProfilePreferences?
}