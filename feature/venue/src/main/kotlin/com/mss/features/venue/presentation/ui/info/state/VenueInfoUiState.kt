package com.mss.features.venue.presentation.ui.info.state

import com.mss.core.domain.Venue
import com.mss.core.domain.VenueDetails
import com.mss.core.ui.model.SocialLink
import com.mss.core.ui.model.common.UiState

data class VenueInfoUiState(
    val venue: Venue?,
    val details: VenueDetails?,
    val links: List<SocialLink>,
    override val isLoading: Boolean,
    override val errorMessageId: Int?,
    override val hasData: Boolean,
) : UiState