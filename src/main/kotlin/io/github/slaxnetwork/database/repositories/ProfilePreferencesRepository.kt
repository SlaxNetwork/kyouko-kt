package io.github.slaxnetwork.database.repositories

import io.github.slaxnetwork.database.models.profile.ProfilePreferencesModel

interface ProfilePreferencesRepository {
    suspend fun create(): Int

    suspend fun findById(id: Int): ProfilePreferencesModel?
}