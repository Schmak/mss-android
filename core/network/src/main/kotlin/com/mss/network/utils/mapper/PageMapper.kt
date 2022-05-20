package com.mss.network.utils.mapper

import com.mss.core.domain.page.Page
import com.mss.core.utils.Mapper
import com.mss.network.model.PageDto

class PageMapper<T, R>(private val mapper: Mapper<T, R>) : Mapper<PageDto<T>, Page<R>> {
    override fun PageDto<T>.map() = Page(
        content = content.map(mapper),
        totalElements = totalElements,
        totalPages = totalPages,
        pageNumber = number,
    )
}