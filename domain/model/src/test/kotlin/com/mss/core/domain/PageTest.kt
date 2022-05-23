package com.mss.core.domain

import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Page.Companion.getPage
import com.mss.core.domain.page.Pageable
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.junit5.AbstractTestCaseWithOrigin
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@UnitTest
internal class PageTest {
    @ParameterizedTest
    @MethodSource("cases")
    fun getPage(case: TestCase) {
        val actual = case.list.getPage(case.request)
        Assertions.assertThat(actual).isEqualTo(case.expected)
    }

    @Suppress("LongMethod")
    private fun cases() = listOf(
        TestCase(
            list = emptyList(),
            request = Pageable.page(page = 0, size = 10),
            expected = emptyPage,
        ),
        TestCase(
            list = emptyList(),
            request = Pageable.page(page = 1, size = 10),
            expected = emptyPage.copy(pageNumber = 1),
        ),
        TestCase(
            request = Pageable.page(page = 0, size = 10),
            expected = Page(
                content = (1..10).toList(),
                totalElements = TOTAL,
                totalPages = 10,
                pageNumber = 0,
            ),
        ),
        TestCase(
            request = Pageable.page(page = 1, size = 10),
            expected = Page(
                content = (11..20).toList(),
                totalElements = TOTAL,
                totalPages = 10,
                pageNumber = 1,
            ),
        ),
        TestCase(
            request = Pageable.page(page = 9, size = 10),
            expected = Page(
                content = (91..95).toList(),
                totalElements = TOTAL,
                totalPages = 10,
                pageNumber = 9,
            ),
        ),
        TestCase(
            request = Pageable.page(page = 10, size = 10),
            expected = Page(
                content = emptyList(),
                totalElements = TOTAL,
                totalPages = 10,
                pageNumber = 10,
            ),
        ),
        TestCase(
            request = Pageable.page(page = 0, size = 1000),
            expected = Page(
                content = defaultList,
                totalElements = TOTAL,
                totalPages = 1,
                pageNumber = 0,
            ),
        ),
        TestCase(
            request = Pageable.page(page = 1, size = 1000),
            expected = Page(
                content = emptyList(),
                totalElements = TOTAL,
                totalPages = 1,
                pageNumber = 1,
            ),
        ),

        )

    data class TestCase(
        val list: List<Int> = defaultList,
        val request: Pageable,
        val expected: Page<Int>,
    ) : AbstractTestCaseWithOrigin()

    companion object {
        private const val TOTAL = 95L
        private val defaultList = (1..TOTAL.toInt()).toList()
        private val emptyPage = Page(
            content = emptyList<Int>(),
            totalElements = 0,
            totalPages = 0,
            pageNumber = 0,
        )
    }
}