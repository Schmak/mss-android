package com.mss.core.network.v4.model

data class PageDto<T>(
    val numberOfElements: Int,
    val totalElements: Long,
    val number: Int,
    val totalPages: Int,
    val content: List<T>
) {
    companion object
}