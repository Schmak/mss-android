package com.mss.core.network.v4.mapper

import com.mss.core.domain.page.Page
import com.mss.core.network.v4.model.PageDto
import com.mss.core.utils.Mapper

class PageMapper<T, R>(private val mapper: Mapper<T, R>) : Mapper<PageDto<T>, Page<R>> {
    override fun PageDto<T>.map() = Page(
        content = content.map(mapper),
        totalElements = totalElements,
        totalPages = totalPages,
        pageNumber = number,
    )
}