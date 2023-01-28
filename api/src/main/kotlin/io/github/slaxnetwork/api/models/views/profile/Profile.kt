package io.github.slaxnetwork.api.models.views.profile

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Profile(
    @Contextual
    val id: UUID,

    @SerialName("rank_id")
    val rankId: String
)