package com.mss.core.utils

import retrofit2.HttpException

private const val NOT_FOUND = 404

suspend fun <R : Any> Result<R>.nullIfNotFound(): Result<R?> =
    if (this is Result.Error && (exception as? HttpException)?.code() == NOT_FOUND)
        Result.of { null }
    else
        this