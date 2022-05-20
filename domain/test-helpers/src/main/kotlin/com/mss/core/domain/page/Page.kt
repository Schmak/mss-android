package com.mss.core.domain.page

fun <T> Page.Companion.create(
    content: List<T> = emptyList(),
    totalElements: Long = content.size.toLong(),
    totalPages: Int = 1,
    pageNumber: Int = 0,
) = Page(
    content = content,
    totalElements = totalElements,
    totalPages = totalPages,
    pageNumber = pageNumber
)