package com.mss.features.venue.domain.usecases

import com.mss.core.domain.Venue
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.domain.usecases.FilterAndOrderResourceLinks
import com.mss.core.utils.Result
import com.mss.core.utils.Result.Companion.map
import javax.inject.Inject

class GetVenueInfo @Inject constructor(
    private val repository: VenueRepository,
    private val filterAndOrderResourceLinks: FilterAndOrderResourceLinks,
) {
    suspend operator fun invoke(venueSlug: String): Result<Venue> =
        repository.getInfo(venueSlug)
            .map { it.copy(resourceLinks = filterAndOrderResourceLinks(it.resourceLinks)) }
}