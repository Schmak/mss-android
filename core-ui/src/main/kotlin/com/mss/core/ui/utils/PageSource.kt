package com.mss.core.ui.utils

import androidx.paging.PagingSource
import com.mss.core.utils.Result

class PageSource<T : Any>(
    private val getPage: suspend (Int) -> Result<List<T>>,
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageNum = params.key ?: 0
        return when (val page = getPage(pageNum)) {
            is Result.Success -> LoadResult.Page(
                data = page.data,
                prevKey = if (pageNum == 0) null else pageNum - 1,
                nextKey = if (page.data.isEmpty()) null else pageNum + 1,
            )
            is Result.Error -> LoadResult.Error(page.exception)
        }
    }

    override fun getRefreshKey(state: androidx.paging.PagingState<Int, T>): Int? = null
}