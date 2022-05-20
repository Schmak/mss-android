package com.mss.core.utils

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    companion object {
        @Suppress("TooGenericExceptionCaught", "FunctionMinLength")
        suspend fun <R> of(
            block: suspend () -> R
        ): Result<R> = try {
            Success(block())
        } catch (e: Exception) {
            Error(e)
        }
    }
}