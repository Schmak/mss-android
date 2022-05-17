package com.mss.features.series.data.repository

import com.mss.annotation.CoroutineTest
import com.mss.annotation.UnitTest
import com.mss.core.utils.Result
import com.mss.domain.SeriesItem
import com.mss.domain.create
import com.mss.domain.page.Page
import com.mss.domain.page.Pageable
import com.mss.domain.ref.SeriesReference
import com.mss.domain.ref.create
import com.mss.features.series.domain.repository.SeriesRepository
import com.mss.junit5.AbstractTestCaseWithOrigin
import com.mss.network.api.SeriesApi
import com.mss.network.model.PageDto
import com.mss.network.model.SeriesInfoDto
import com.mss.network.model.SeriesItemDto
import com.mss.network.model.create
import com.mss.network.model.ref.EventReferenceDto
import com.mss.network.model.ref.SeriesReferenceDto
import com.mss.network.model.ref.create
import com.mss.utils.DEFAULT_LOCAL_DATE
import com.mss.utils.coroutines.TestDispatchers
import com.mss.utils.coroutines.verify
import io.mockk.MockKMatcherScope
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.time.Duration.Companion.days

@UnitTest
@CoroutineTest
@OptIn(ExperimentalCoroutinesApi::class)
internal class SeriesRepositoryImplTest {
    private val seriesApi: SeriesApi = mockk()
    private val repository: SeriesRepository = SeriesRepositoryImpl(
        api = seriesApi, dispatcher = TestDispatchers.IO
    )

    @ParameterizedTest
    @MethodSource("cases")
    fun `check dispatcher`(case: TestCase<*, *>) = runTest {
        verify { case.apiQuery(this) } isCalledFrom case.repositoryQuery with TestDispatchers.IO
    }

    @ParameterizedTest
    @MethodSource("cases")
    fun success(case: TestCase<*, *>) = runTest {
        coEvery { case.apiQuery(this) } returns case.apiResponse

        val actual = case.repositoryQuery()
        coVerify(exactly = 1) { case.apiQuery(this) }
        assertThat(actual).isEqualTo(Result.Success(case.expectedRepositoryResponse))
    }

    @ParameterizedTest
    @MethodSource("cases")
    fun failing(case: TestCase<*, *>) = runTest {
        coEvery { case.apiQuery(this) } throws EXCEPTION

        val actual = case.repositoryQuery()
        coVerify(exactly = 1) { case.apiQuery(this) }
        assertThat(actual).isEqualTo(Result.Error(EXCEPTION))
    }

    @Suppress("LongMethod")
    private fun cases() = listOf(
        TestCase(
            name = "getCategories",
            apiQuery = { seriesApi.getCategories() },
            apiResponse = CATEGORIES,
            repositoryQuery = { repository.getCategories() },
            expectedRepositoryResponse = CATEGORIES,
        ),
        TestCase(
            name = "getRegions",
            apiQuery = { seriesApi.getRegions() },
            apiResponse = REGIONS,
            repositoryQuery = { repository.getRegions() },
            expectedRepositoryResponse = REGIONS,
        ),
        TestCase(
            name = "getLeadingSeries",
            apiQuery = { seriesApi.getGoldenSeries() },
            apiResponse = listOf(
                SeriesInfoDto.create(
                    series = SeriesReferenceDto.create(
                        name = SERIES,
                        picture = "$SERIES pic",
                    )
                )
            ),
            repositoryQuery = { repository.getLeadingSeries(pageable = page(0)) },
            expectedRepositoryResponse = Page(
                content = listOf(
                    SeriesReference.create(
                        name = SERIES, picture = "$SERIES pic"
                    )
                ),
                totalElements = 1,
                totalPages = 1,
                pageNumber = 0,
            )
        ),
        TestCase(
            name = "getCollection", apiQuery = {
                seriesApi.getCollection(
                    region = REGIONS[0],
                    category = CATEGORIES[0],
                    page = 12,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = PageDto.create(
                listOf(SERIES_ITEM_DTO),
                totalPages = TOTAL_PAGES,
                totalElements = TOTAL_ELEMENTS,
                number = 12,
            ),
            repositoryQuery = {
                repository.getCollection(
                    region = REGIONS[0], category = CATEGORIES[0], pageable = page(12)
                )
            },
            expectedRepositoryResponse = Page(
                content = listOf(EXPECTED_SERIES_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = 12
            )
        ),
        TestCase(
            name = "getMostRecent",
            apiQuery = {
                seriesApi.getSeries(
                    page = 23,
                    size = PAGE_SIZE,
                    filterIds = any(),
                    orderBy = any(),
                    orderDescending = any()
                )
            },
            apiResponse = PageDto.create(
                content = listOf(SERIES_ITEM_DTO),
                totalPages = TOTAL_PAGES,
                totalElements = TOTAL_ELEMENTS,
                number = 23,
            ),
            repositoryQuery = { repository.getMostRecent(pageable = page(23)) },
            expectedRepositoryResponse = Page(
                content = listOf(EXPECTED_SERIES_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = 23,
            ),
        ),
    )

    data class TestCase<A, R>(
        val name: String,
        val apiQuery: suspend MockKMatcherScope.() -> A,
        val apiResponse: A,
        val repositoryQuery: suspend () -> R,
        val expectedRepositoryResponse: R,
    ) : AbstractTestCaseWithOrigin() {
        override fun toString() = name
    }

    private companion object {
        private const val SERIES = "Formula One"
        private const val PAGE_SIZE = 17
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val EXCEPTION = RuntimeException("Some exception")

        private val CATEGORIES = listOf("Single Seater", "Motorcycle")
        private val REGIONS = listOf("Worldwide", "Europe", "United States")

        private val SERIES_ITEM_DTO = SeriesItemDto.create(
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = EventReferenceDto.create(
                name = "$SERIES event",
            ),
            lastEventDate = DEFAULT_LOCAL_DATE.toEpochDay().days.inWholeSeconds
        )
        private val EXPECTED_SERIES_ITEM = SeriesItem.create(
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = SeriesItem.LastEvent(
                name = "$SERIES event",
                date = DEFAULT_LOCAL_DATE,
            )
        )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}