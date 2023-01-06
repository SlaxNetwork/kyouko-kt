package io.github.slaxnetwork.api.exceptions

import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class RouteError(
    @SerialName("status_code")
    val statusCode: Int,
    override val message: String?,
): RuntimeException(message) {
    constructor(statusCode: HttpStatusCode, message: String?) :
            this(statusCode.value, message)

    // TODO: 12/31/2022 switch to data objects?

    /**
     * Whenever an invalid id is passed in the request.
     */
    object InvalidId : RouteError(400, "invalid id passed")

    /**
     * Whenever an invalid body is passed in the request.
     */
    object InvalidBody : RouteError(400, "invalid body passed")

    /**
     * Unable to find the requested resource.
     */
    object NotFound : RouteError(404, "resource not found")

    /**
     * Resource already exists.
     */
    object Exists : RouteError(409, "resource already exists")

    /**
     * User is being rate-limited from requests to the API.
     */
    object RateLimited : RouteError(429, "too many requests")

    /**
     * User is unauthorized to access route of API.
     */
    object Unauthorized : RouteError(401, "unauthorized request")
}