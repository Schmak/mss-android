package com.mss.core.data.repository

import com.mss.core.domain.VenueItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.network.v3.api.SeasonApi
import com.mss.core.network.v3.api.VenueApi
import com.mss.core.network.v3.mapper.VenueItemMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class VenueRepositoryImpl(
    private val seasonApi: SeasonApi,
    private val venueApi: VenueApi,
    private val dispatcher: CoroutineDispatcher,
) : VenueRepository {
    override suspend fun getSeasonVenues(
        season: String,
        pageable: Pageable
    ): Result<Page<VenueItem>> = withContext(dispatcher) {
        Result.of {
            seasonApi.getVenues(
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
            venueApi.getVenues(
                filterIds = arrayOf(collection.title),
                page = pageable.page,
                size = pageable.size,
            ).let(VenueItemMapper.pageMapper)
        }
    }
}