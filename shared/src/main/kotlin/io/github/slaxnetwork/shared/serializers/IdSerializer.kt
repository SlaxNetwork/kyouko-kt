package io.github.slaxnetwork.shared.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.litote.kmongo.Id
import org.litote.kmongo.id.IdGenerator

internal class IdSerializer<T : Id<*>> : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("IdSerializer", PrimitiveKind.STRING)

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(decoder: Decoder): T =
        IdGenerator.defaultGenerator.create(decoder.decodeString()) as T

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(value.toString())
    }
}