package com.mss.network.model

fun <T> PageDto.Companion.create(
    content: List<T>,
    totalElements: Long = content.size.toLong(),
    totalPages: Int = 1,
    numberOfElements: Int = content.size,
    number: Int = 0,
) = PageDto(
    content = content,
    totalElements = totalElements,
    totalPages = totalPages,
    numberOfElements = numberOfElements,
    number = number
)