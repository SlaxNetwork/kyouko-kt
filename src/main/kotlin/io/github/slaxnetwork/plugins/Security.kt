package io.github.slaxnetwork.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

// security 100
const val EXTREMELY_SECURE_TOKEN_THAT_WILL_NEVER_BE_LEAKED = "kyouko"

fun Application.configureSecurity() {
    // TODO: 1/2/2023 secure better lol
    authentication {
        bearer("bearer") {
            realm = "kyouko server"
            authenticate { tokenCredential ->
                if(tokenCredential.token == EXTREMELY_SECURE_TOKEN_THAT_WILL_NEVER_BE_LEAKED) {
                    PrincipalResult.Authorized
                } else {
                    null
                }
            }
        }
    }
}

/**
 * Authorization result.
 */
sealed interface PrincipalResult : Principal {
    /**
     * Authorized request with primary service token.
     */
    object Authorized : PrincipalResult

    /**
     * Third-party request that is authorized to make requests.
     */
    object ThirdPartyAuthorized : PrincipalResult

    /**
     * Unauthorized request.
     */
    object Unauthorized : PrincipalResult

    /**
     * @return Whether the request is an authorized request or not.
     */
    val authorized: Boolean
        get() = this is Authorized

    /**
     * @return Whether the authorized request comes from a third party service.
     */
    val thirdParty: Boolean
        get() = this is ThirdPartyAuthorized
}