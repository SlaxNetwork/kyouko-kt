package io.github.slaxnetwork.utils

import java.util.*

fun String.toUUID(): UUID? {
    return try {
        UUID.fromString(this)
    } catch(ex: IllegalArgumentException) {
        null
    }
}