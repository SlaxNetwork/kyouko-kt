package io.github.slaxnetwork.api.types

import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentType
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.utils.wrapResponse
import io.ktor.client.request.*
import java.util.UUID

object ModerationAPI {
    /**
     * Get either all punishments of a player or all punishments
     * matching a specific [PunishmentType].
     *
     * @param uuid Player UUID
     * @param type Punishment type
     * @return list of all matching [Punishment]
     */
    suspend fun getAllPunishments(
        uuid: UUID,
        type: PunishmentType? = null
    ): Result<List<Punishment>> {
        return wrapResponse(client.get("moderation/punishment/$uuid") {
            parameter("type", type)
        })
    }

    /**
     * Get the active punishment of a player if one exists.
     *
     * @param uuid Player UUID
     * @param type Punishment type
     * @return the active [Punishment] or an exception if none was found
     */
    suspend fun getActivePunishment(uuid: UUID, type: PunishmentType): Result<Punishment> {
        return wrapResponse(client.get("moderation/punishment/$uuid") {
            parameter("type", type)
            parameter("active", true)
        })
    }
}