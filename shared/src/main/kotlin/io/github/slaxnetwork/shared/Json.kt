package io.github.slaxnetwork.shared

import io.github.slaxnetwork.shared.serializers.IdSerializer
import io.github.slaxnetwork.shared.serializers.InstantSerializer
import io.github.slaxnetwork.shared.serializers.UUIDSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.litote.kmongo.Id
import org.litote.kmongo.id.StringId
import java.time.Instant
import java.util.*

val JSON = Json {
    ignoreUnknownKeys = true

    serializersModule = SerializersModule {
        contextual(UUID::class, UUIDSerializer)
        contextual(Instant::class, InstantSerializer)

        contextual(Id::class, IdSerializer())
        contextual(StringId::class, IdSerializer())
    }
}