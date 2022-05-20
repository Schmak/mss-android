package com.mss.core.ui.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.page.Pageable.Companion.page
import com.mss.core.utils.Result

class PageSource<T : Any>(
    private val targetPageSize: Int = 10,
    private val getPage: suspend (Pageable) -> Result<Page<T>>,
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageNum = params.key ?: 0
        val pageable = page(page = pageNum, size = targetPageSize)
        return when (val result = getPage(pageable)) {
            is Result.Success -> {
                val page = result.data
                LoadResult.Page(
                    data = page.content,
                    prevKey = (pageNum - 1).takeIf { it >= 0 },
                    nextKey = (pageNum + 1).takeIf { it < page.totalPages },
                )
            }
            is Result.Error -> LoadResult.Error(result.exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
}