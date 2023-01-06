package io.github.slaxnetwork.api.models.rank

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rank(
    @Contextual
    @SerialName("_id")
    val id: String
) {
    companion object {
        const val DEFAULT_RANK_ID = "default"
    }
}