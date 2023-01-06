package io.github.slaxnetwork.shared.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

internal object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        var str = decoder.decodeString()
        // oh god.
        // TODO: 1/1/2023 please fix this, use binary conversion instead?
        if(str.length == 32) {
            val sb = StringBuilder(str)
            sb.insert(8, "-")
            sb.insert(13, "-")
            sb.insert(18, "-")
            sb.insert(23, "-")

            str = sb.toString()
        }

        return UUID.fromString(str)
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}