package com.mss.core.ui.utils

import androidx.paging.PagingSource
import com.mss.core.domain.page.Page.Companion.getPage
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.page.Pageable.Companion.page
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.junit5.AbstractTestCaseWithOrigin
import com.mss.core.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@ExperimentalCoroutinesApi
@UnitTest
internal class PageSourceTest {
    @ParameterizedTest
    @MethodSource("cases")
    fun load(case: TestCase) = runTest {
        val source =
            PageSource(targetPageSize = case.targetPageSize) { Result.of { case.content.getPage(it) } }
        val result = source.load(case.loadParams)

        assertThat(result).isEqualTo(case.expected)
    }

    @Suppress("LongMethod")
    private fun cases() = listOf(
        TestCase(
            content = emptyList(),
            request = page(page = 0),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null,
            )
        ),
        TestCase(
            content = emptyList(),
            request = page(page = 1),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = 0,
                nextKey = null,
            )
        ),
        TestCase(
            request = page(page = 0),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = (1..10).toList(),
                prevKey = null,
                nextKey = 1,
            )
        ),
        TestCase(
            request = page(page = 0),
            targetPageSize = 15,
            expected = PagingSource.LoadResult.Page(
                data = (1..15).toList(),
                prevKey = null,
                nextKey = 1,
            )
        ),
        TestCase(
            request = page(page = 1),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = (11..20).toList(),
                prevKey = 0,
                nextKey = 2,
            )
        ),
        TestCase(
            request = page(page = 1),
            targetPageSize = 15,
            expected = PagingSource.LoadResult.Page(
                data = (16..30).toList(),
                prevKey = 0,
                nextKey = 2,
            )
        ),
        TestCase(
            request = page(page = 6),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = (61..70).toList(),
                prevKey = 5,
                nextKey = 7,
            )
        ),
        TestCase(
            request = page(page = 6),
            targetPageSize = 15,
            expected = PagingSource.LoadResult.Page(
                data = (91..95).toList(),
                prevKey = 5,
                nextKey = null,
            )
        ),
        TestCase(
            request = page(page = 7),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = (71..80).toList(),
                prevKey = 6,
                nextKey = 8,
            )
        ),
        TestCase(
            request = page(page = 7),
            targetPageSize = 15,
            expected = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = 6,
                nextKey = null,
            )
        ),
        TestCase(
            request = page(page = 9),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = (91..95).toList(),
                prevKey = 8,
                nextKey = null,
            )
        ),
        TestCase(
            request = page(page = 10),
            targetPageSize = 10,
            expected = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = 9,
                nextKey = null,
            )
        ),

        )

    data class TestCase(
        val content: List<Int> = defaultList,
        val request: Pageable,
        val targetPageSize: Int,
        val expected: PagingSource.LoadResult.Page<Int, Int>,
    ) : AbstractTestCaseWithOrigin() {
        val loadParams = PagingSource.LoadParams.Refresh(
            key = request.page,
            loadSize = request.size,
            placeholdersEnabled = false,
        )

        override fun toString() = "request=$request, targetPageSize=$targetPageSize"
    }

    companion object {
        private const val TOTAL = 95L
        private val defaultList = (1..TOTAL.toInt()).toList()

        private fun page(page: Int) = page(page, size = 15)
    }
}