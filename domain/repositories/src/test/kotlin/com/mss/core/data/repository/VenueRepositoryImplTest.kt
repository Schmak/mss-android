package com.mss.core.data.repository

import com.mss.annotation.UnitTest
import com.mss.core.domain.VenueItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.create
import com.mss.core.domain.repository.VenueRepository
import com.mss.network.api.SeasonApi
import com.mss.network.api.VenueApi
import com.mss.network.model.PageDto
import com.mss.network.model.VenueItemDto
import com.mss.network.model.create
import com.mss.network.model.ref.CountryReferenceDto
import com.mss.network.model.ref.create
import com.mss.test.BaseRepositoryTest
import com.mss.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class VenueRepositoryImplTest : BaseRepositoryTest() {
    private val venueApi: VenueApi = mockk()
    private val seasonApi: SeasonApi = mockk()
    private val repository: VenueRepository =
        VenueRepositoryImpl(
            seasonApi = seasonApi,
            venueApi = venueApi,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeasonVenues",
            apiQuery = {
                seasonApi.getVenues(
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
                venueApi.getVenues(
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