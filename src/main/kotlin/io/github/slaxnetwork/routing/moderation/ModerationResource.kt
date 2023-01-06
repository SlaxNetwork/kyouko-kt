package io.github.slaxnetwork.routing.moderation

import io.github.slaxnetwork.api.models.staff.punishment.PunishmentType
import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/moderation") @Serializable
class ModerationResource {
    @Resource("punishment") @Serializable
    class Punishment(
        val parent: ModerationResource = ModerationResource()
    ) {
        @Resource("{uuid}") @Serializable
        class Id(
            val parent: Punishment,
            val uuid: String,

            val type: PunishmentType? = null,
            val active: Boolean? = null
        ) {
            @Resource("issue") @Serializable
            class Issue(
                val parent: Id
            )

            @Resource("revert") @Serializable
            class Revert(
                val parent: Id,
                val type: PunishmentType
            )
        }
    }
}