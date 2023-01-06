package io.github.slaxnetwork.utils

import io.github.slaxnetwork.plugins.PrincipalResult
import io.ktor.server.application.*
import io.ktor.server.auth.*

val ApplicationCall.requestPrincipal: PrincipalResult?
    get() = principal()

val ApplicationCall.authorized: Boolean
    get() = requestPrincipal?.authorized == true