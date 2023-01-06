package io.github.slaxnetwork.api

import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import io.github.slaxnetwork.api.models.staff.punishment.PunishmentType
import io.github.slaxnetwork.api.utils.client
import io.github.slaxnetwork.api.utils.wrapResponse
import io.ktor.client.request.*
import java.util.UUID

object ModerationAPI {
    suspend fun getActivePunishment(uuid: UUID, type: PunishmentType): Result<Punishment> {
        return wrapResponse(client.get("moderation/punishment/$uuid") {
            parameter("type", type)
            parameter("active", true)
        })
    }
}