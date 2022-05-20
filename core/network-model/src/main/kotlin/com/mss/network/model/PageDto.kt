package com.mss.network.model

data class PageDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val numberOfElements: Int,
    val number: Int,
) {
    companion object
}