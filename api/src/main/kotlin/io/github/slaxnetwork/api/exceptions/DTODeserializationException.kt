package io.github.slaxnetwork.api.exceptions

class DTODeserializationException(fieldName: String)
    : RuntimeException("failed to deserialize field $fieldName as it had no value.")

internal typealias DTOException = DTODeserializationException