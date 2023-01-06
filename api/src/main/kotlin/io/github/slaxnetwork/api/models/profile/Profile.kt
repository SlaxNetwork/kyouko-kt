package io.github.slaxnetwork.api.models.profile

import io.github.slaxnetwork.api.models.VersionedDocument
import io.github.slaxnetwork.api.models.rank.Rank
import io.github.slaxnetwork.api.models.staff.punishment.Punishment
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import java.util.UUID

@Serializable
data class Profile(
    @Contextual
    @SerialName("_id")
    val uuid: UUID,

    val username: String,

    @SerialName("rank_id")
    val rankId: String = "default",

//    @SerialName("game_profile")
//    val gameProfile: GameProfile,

    val punishments: Set<@Contextual Id<@Contextual Punishment>> = emptySet(),
): VersionedDocument {
    constructor(uuid: UUID, username: String)
            : this(uuid, username, Rank.DEFAULT_RANK_ID)
}