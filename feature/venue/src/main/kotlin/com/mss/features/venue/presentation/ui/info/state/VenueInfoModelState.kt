package com.mss.features.venue.presentation.ui.info.state

import com.mss.core.domain.Venue
import com.mss.core.domain.VenueDetails
import com.mss.core.ui.utils.SocialLinkMapper

data class VenueInfoModelState(
    val venueInfo: Venue? = null,
    val venueDetails: VenueDetails? = null,
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null,
) {
    fun toUiState() = VenueInfoUiState(
        venue = venueInfo,
        details = venueDetails,
        isLoading = isLoading,
        errorMessageId = errorMessageId,
        hasData = venueInfo != null,
        links = venueInfo?.resourceLinks?.map(SocialLinkMapper).orEmpty()
    )
}