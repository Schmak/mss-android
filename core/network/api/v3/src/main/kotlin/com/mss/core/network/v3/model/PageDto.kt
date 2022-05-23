package com.mss.core.network.v3.model

data class PageDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val numberOfElements: Int,
    val number: Int,
) {
    companion object
}