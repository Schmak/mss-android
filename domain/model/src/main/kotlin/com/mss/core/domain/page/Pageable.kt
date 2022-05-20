package com.mss.core.domain.page

interface Pageable {
    val page: Int
    val size: Int

    companion object {
        fun page(page: Int, size: Int): Pageable = PageableImpl(page, size)
    }
}