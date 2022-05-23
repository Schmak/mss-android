package com.mss.core.data.repository

import com.mss.core.domain.VenueItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.VenueApiV3
import com.mss.core.network.v3.mapper.VenueItemMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class VenueRepositoryImpl(
    private val seasonApiV3: SeasonApiV3,
    private val venueApiV3: VenueApiV3,
    private val dispatcher: CoroutineDispatcher,
) : VenueRepository {
    override suspend fun getSeasonVenues(
        season: String,
        pageable: Pageable
    ): Result<Page<VenueItem>> = withContext(dispatcher) {
        Result.of {
            seasonApiV3.getVenues(
                season = season,
                page = pageable.page,
                size = pageable.size,
            ).let(VenueItemMapper.pageMapper)
        }
    }

    override suspend fun getCollection(
        collection: VenueRepository.Collection,
        pageable: Pageable
    ): Result<Page<VenueItem>> = withContext(dispatcher) {
        Result.of {
            venueApiV3.getVenues(
                filterIds = arrayOf(collection.title),
                page = pageable.page,
                size = pageable.size,
            ).let(VenueItemMapper.pageMapper)
        }
    }
}