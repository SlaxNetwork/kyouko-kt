package io.github.slaxnetwork.api.utils

import io.github.slaxnetwork.api.exceptions.RouteError
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal suspend inline fun <reified T> wrapResponse(res: HttpResponse): Result<T> {
    if(!res.status.isSuccess()) {
        val error = res.body<RouteError>()
        return Result.failure(RouteError(error.statusCode, error.message))
    }
    return Result.success(res.body())
}
