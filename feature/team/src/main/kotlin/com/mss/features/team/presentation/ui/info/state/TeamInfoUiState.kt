package com.mss.features.team.presentation.ui.info.state

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.ui.model.SocialLink
import com.mss.core.ui.model.common.UiState

data class TeamInfoUiState(
    val name: String,
    val picture: String?,
    val constructors: List<String>,
    val firstSeries: SeriesWithDrivers?,
    val otherSeries: List<SeriesWithDrivers>,
    val links: List<SocialLink>,
    override val isLoading: Boolean,
    override val errorMessageId: Int?,
    override val hasData: Boolean,
) : UiState