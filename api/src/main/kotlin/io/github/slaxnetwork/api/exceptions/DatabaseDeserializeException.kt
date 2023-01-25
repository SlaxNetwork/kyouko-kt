package io.github.slaxnetwork.api.exceptions

import kotlin.reflect.KProperty1

open class DatabaseDeserializeException(
    property: KProperty1<*, *>,
    message: String? = null
) : RuntimeException(message ?: "failed to deserialize the database value ${property.name}")