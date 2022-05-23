package com.mss.core.data.repository

import com.mss.core.domain.SessionItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.repository.SessionRepository
import com.mss.core.network.v4.api.SessionApiV4
import com.mss.core.network.v4.model.PageDto
import com.mss.core.network.v4.model.SessionItemDto
import com.mss.core.network.v4.model.create
import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.SessionReferenceDto
import com.mss.core.network.v4.model.ref.create
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class SessionRepositoryImplTest : AbstractRepositoryTest() {
    private val sessionApiV4: SessionApiV4 = mockk()
    private val repository: SessionRepository =
        SessionRepositoryImpl(
            sessionApiV4 = sessionApiV4,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getCollection",
            apiQuery = {
                sessionApiV4.getCollection(
                    SessionApiV4.SessionCollection.MostRecent,
                    status = SessionApiV4.SeriesStatus.Active,
                    page = 16,
                    size = PAGE_SIZE
                )
            },
            apiResponse = apiResponse(15),
            repositoryQuery = {
                repository.getCollection(
                    collection = SessionRepository.Collection.MostRecent,
                    pageable = page(16)
                )
            },
            expectedRepositoryResponse = repoResponse(15),
        ),
        TestCase(
            name = "getSeriesCategory",
            apiQuery = {
                sessionApiV4.getSeriesCategorySessions(
                    category = CATEGORY,
                    status = SessionApiV4.SeriesStatus.Active,
                    page = 17,
                    size = PAGE_SIZE
                )
            },
            apiResponse = apiResponse(18),
            repositoryQuery = {
                repository.getSeriesCategorySessions(
                    category = CATEGORY,
                    pageable = page(17)
                )
            },
            expectedRepositoryResponse = repoResponse(18),
        ),
    )

    companion object {
        private const val CATEGORY = "Single Seater"
        private const val SERIES_NAME = "Formula One"
        private const val SERIES_SLUG = "formula-one"
        private const val SESSION_NAME = "Race"
        private const val SESSION_SLUG = "race"
        private const val EVENT_NAME = "Portugal"
        private const val EVENT_SLUG = "portugal"
        private const val PAGE_SIZE = 27
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val SESSION_ITEM = SessionItem(
            slug = SESSION_SLUG,
            name = SESSION_NAME,
            event = EventReference(name = EVENT_NAME, slug = EVENT_SLUG),
            series = SeriesReference(name = SERIES_NAME, slug = SERIES_SLUG, picture = null),
            startTime = null,
        )

        private val SESSION_ITEM_DTO = SessionItemDto.create(
            session = SessionReferenceDto.create(
                slug = SESSION_SLUG,
                name = SESSION_NAME,
            ),
            event = EventReferenceDto.create(name = EVENT_NAME, slug = EVENT_SLUG),
            series = SeriesReferenceDto.create(
                name = SERIES_NAME,
                slug = SERIES_SLUG,
                picture = null
            ),
            startTime = null,
            startTimeUtc = null,
        )

        private fun repoResponse(page: Int) =
            Page(
                content = listOf(SESSION_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = page,
            )

        private fun apiResponse(page: Int) =
            PageDto.create(
                content = listOf(SESSION_ITEM_DTO),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                number = page,
            )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}