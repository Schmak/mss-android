package com.mss.core.data.repository

import com.mss.annotation.UnitTest
import com.mss.core.domain.VenueItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.create
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.VenueApiV3
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.VenueItemDto
import com.mss.core.network.v3.model.create
import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.create
import com.mss.test.BaseRepositoryTest
import com.mss.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class VenueRepositoryImplTest : BaseRepositoryTest() {
    private val venueApiV3: VenueApiV3 = mockk()
    private val seasonApiV3: SeasonApiV3 = mockk()
    private val repository: VenueRepository =
        VenueRepositoryImpl(
            seasonApiV3 = seasonApiV3,
            venueApiV3 = venueApiV3,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeasonVenues",
            apiQuery = {
                seasonApiV3.getVenues(
                    season = SEASON,
                    page = 12,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = apiResponse(11),
            repositoryQuery = {
                repository.getSeasonVenues(
                    season = SEASON,
                    pageable = page(12)
                )
            },
            expectedRepositoryResponse = repoResponse(11),
        ),
        TestCase(
            name = "getCollection",
            apiQuery = {
                venueApiV3.getVenues(
                    filterIds = arrayOf("Race Circuit"),
                    page = 16,
                    size = PAGE_SIZE
                )
            },
            apiResponse = apiResponse(15),
            repositoryQuery = {
                repository.getCollection(
                    collection = VenueRepository.Collection.RaceCircuit,
                    pageable = page(16)
                )
            },
            expectedRepositoryResponse = repoResponse(15),
        ),
    )

    companion object {
        private const val SEASON = "F1 2022"
        private const val VENUE_NAME = "Ferrari"
        private const val VENUE_SLUG = "ferrari"
        private const val PAGE_SIZE = 27
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val VENUE_ITEM = VenueItem(
            slug = VENUE_SLUG,
            name = VENUE_NAME,
            picture = null,
            country = CountryReference.create(
                name = "country name",
                picture = "country flag",
            ),
        )

        private val VENUE_ITEM_DTO = VenueItemDto.create(
            uuid = VENUE_SLUG,
            name = VENUE_NAME,
            picture = null,
            country = CountryReferenceDto.create(
                name = "country name",
                picture = "country flag",
            ),
        )

        private fun repoResponse(page: Int) =
            Page(
                content = listOf(VENUE_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = page,
            )

        private fun apiResponse(page: Int) =
            PageDto.create(
                content = listOf(VENUE_ITEM_DTO),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                number = page,
            )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}