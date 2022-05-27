package com.mss.core.data.repository

import com.mss.core.domain.Venue
import com.mss.core.domain.VenueDetails
import com.mss.core.domain.VenueItem
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.VenueApiV3
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.VenueItemDto
import com.mss.core.network.v3.model.create
import com.mss.core.network.v3.model.ref.create
import com.mss.core.network.v4.api.VenueApiV4
import com.mss.core.network.v4.model.VenueDetailsDto
import com.mss.core.network.v4.model.VenueDto
import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.create
import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.create
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk
import com.mss.core.network.v3.model.ref.CountryReferenceDto as CountryReferenceDtoV3
import com.mss.core.network.v4.model.ref.CountryReferenceDto as CountryReferenceDtoV4

@UnitTest
internal class VenueRepositoryImplTest : AbstractRepositoryTest() {
    private val venueApiV3: VenueApiV3 = mockk()
    private val venueApiV4: VenueApiV4 = mockk()
    private val seasonApiV3: SeasonApiV3 = mockk()
    private val repository: VenueRepository =
        VenueRepositoryImpl(
            seasonApiV3 = seasonApiV3,
            venueApiV3 = venueApiV3,
            venueApiV4 = venueApiV4,
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
        TestCase(
            name = "getInfo",
            apiQuery = {
                venueApiV4.getInfo(venue = VENUE_SLUG)
            },
            apiResponse = VENUE_DTO,
            repositoryQuery = {
                repository.getInfo(venueSlug = VENUE_SLUG)
            },
            expectedRepositoryResponse = VENUE,
        ),
        TestCase(
            name = "getDetails",
            apiQuery = {
                venueApiV4.getDetails(venue = VENUE_SLUG)
            },
            apiResponse = VENUE_DETAILS_DTO,
            repositoryQuery = {
                repository.getDetails(venueSlug = VENUE_SLUG)
            },
            expectedRepositoryResponse = VENUE_DETAILS,
        ),
    )

    companion object {
        private const val SEASON = "F1 2022"
        private const val VENUE_NAME = "Monaco"
        private const val VENUE_SLUG = "monaco"
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
            country = CountryReferenceDtoV3.create(
                name = "country name",
                picture = "country flag",
            ),
        )

        private val VENUE = Venue(
            name = "venue.name",
            picture = "venue.picture",
            country = CountryReference(
                name = "country.name",
                picture = "country.picture",
            ),
            address = "venue.address",
            resourceLinks = listOf(ResourceLink.Facebook("facebook.url")),
        )

        private val VENUE_DTO = VenueDto.create(
            name = "venue.name",
            picture = "venue.picture",
            country = CountryReferenceDtoV4.create(
                name = "country.name",
                picture = "country.picture",
            ),
            address = "venue.address",
            resourceLinks = listOf(
                ResourceLinkDto.create(
                    type = "facebook",
                    href = "facebook.url",
                )
            ),
        )

        private val VENUE_DETAILS = VenueDetails(
            firstEvent = VenueDetails.EventYear(
                event = EventReference(
                    name = "firstEvent.name",
                    slug = "firstEvent.slug",
                ),
                year = 1999,
            ),
            lastEvent = VenueDetails.EventYear(
                event = EventReference(
                    name = "lastEvent.name",
                    slug = "lastEvent.slug",
                ),
                year = 2021,
            ),
            lastCircuit = VenueDetails.Circuit(
                lengthMeters = 2024.0,
                totalCorners = 15,
            ),
            series = listOf(
                SeriesReference(
                    name = "series.name",
                    slug = "series.slug",
                    picture = "series.picture"
                )
            )
        )

        private val VENUE_DETAILS_DTO = VenueDetailsDto.create(
            firstEvent = VenueDetailsDto.EventYear.create(
                event = EventReferenceDto.create(
                    name = "firstEvent.name",
                    slug = "firstEvent.slug",
                ),
                year = 1999,
            ),
            lastEvent = VenueDetailsDto.EventYear.create(
                event = EventReferenceDto.create(
                    name = "lastEvent.name",
                    slug = "lastEvent.slug",
                ),
                year = 2021,
            ),
            lastCircuit = VenueDetailsDto.Circuit.create(
                lengthMeters = 2024.0,
                totalCorners = 15,
            ),
            series = listOf(
                SeriesReferenceDto.create(
                    name = "series.name",
                    slug = "series.slug",
                    picture = "series.picture"
                )
            )
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