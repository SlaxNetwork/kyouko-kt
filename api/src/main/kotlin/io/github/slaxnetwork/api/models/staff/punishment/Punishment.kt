package io.github.slaxnetwork.api.models.staff.punishment

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant
import java.util.UUID
import kotlin.time.Duration

@Serializable
data class Punishment(
    @Contextual
    @SerialName("_id")
    val id: Id<Punishment> = newId(),

    @Contextual
    val issuer: UUID,

    @Contextual
    val issued: UUID,

    val type: PunishmentType,

    val reason: String = "No reason specified.",

    val duration: Duration? = null,

    val active: Boolean = true,

    val revert: PunishmentRevert? = null,

    @Contextual
    val timestamp: Instant = Instant.now()
) {
    constructor(issuer: UUID, issued: UUID, type: PunishmentType, reason: String = "No reason specified.", duration: Duration? = null)
            : this(newId(), issuer, issued, type, reason, duration)

    /**
     * Get whether the issued punishment is temporary.
     */
    val isTemporary: Boolean
        get() {
            if(type == PunishmentType.KICK || type == PunishmentType.WARN) {
                return true
            }

            return duration != null
        }
}