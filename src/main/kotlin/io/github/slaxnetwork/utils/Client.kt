package io.github.slaxnetwork.utils

import io.github.slaxnetwork.shared.createHttpClient

internal val client by lazy {
    createHttpClient("kyouko")
}
