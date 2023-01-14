package io.github.slaxnetwork.shared

import io.github.slaxnetwork.shared.serializers.InstantSerializer
import io.github.slaxnetwork.shared.serializers.UUIDSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.Instant
import java.util.*

val JSON = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true

    serializersModule = SerializersModule {
        contextual(UUID::class, UUIDSerializer)
        contextual(Instant::class, InstantSerializer)
    }
}