package io.github.slaxnetwork.api.models

import kotlinx.serialization.SerialName

interface VersionedDocument {
    @SerialName("__v")
    val version: Int
        get() = 1
}