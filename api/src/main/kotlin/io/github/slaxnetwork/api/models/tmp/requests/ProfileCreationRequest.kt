package io.github.slaxnetwork.api.models.tmp.requests

import kotlinx.serialization.Contextual
import java.util.UUID

@kotlinx.serialization.Serializable
data class ProfileCreationRequest(
    @Contextual
    val id: UUID
)