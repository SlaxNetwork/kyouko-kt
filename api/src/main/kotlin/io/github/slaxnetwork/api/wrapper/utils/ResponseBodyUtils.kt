package io.github.slaxnetwork.api.wrapper.utils

import io.github.slaxnetwork.api.exceptions.RouteError
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal suspend inline fun <reified T> HttpResponse.bodyAsResult(): Result<T> {
    if(!status.isSuccess()) {
        val routeErrorData = body<RouteError>()

        return Result.failure(RouteError(
            routeErrorData.statusCode,
            routeErrorData.message
        ))
    }
    return Result.success(body())
}