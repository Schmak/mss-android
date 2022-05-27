package com.mss.features.venue.domain.usecases

import com.mss.core.domain.VenueDetails
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetVenueDetails @Inject constructor(
    private val repository: VenueRepository,
) {
    suspend operator fun invoke(venueSlug: String): Result<VenueDetails?> =
        repository.getDetails(venueSlug)
}