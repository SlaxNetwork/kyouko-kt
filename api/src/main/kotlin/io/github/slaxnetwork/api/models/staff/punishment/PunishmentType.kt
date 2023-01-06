package io.github.slaxnetwork.api.models.staff.punishment

import kotlinx.serialization.Serializable

@Serializable
enum class PunishmentType {
    WARN,
    KICK,
    MUTE,
    BAN,
    BLACKLIST
}