package io.github.slaxnetwork.api.models.staff.punishment

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class PunishmentRevert(
    @Contextual
    val issuer: UUID,

    val reason: String,

    @Contextual
    val timestamp: Instant = Instant.now()
)