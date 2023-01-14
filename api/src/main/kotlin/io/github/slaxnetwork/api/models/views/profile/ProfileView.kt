package io.github.slaxnetwork.api.models.views.profile

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProfileView(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String
)