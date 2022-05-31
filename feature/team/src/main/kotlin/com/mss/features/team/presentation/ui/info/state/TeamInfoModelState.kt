package com.mss.features.team.presentation.ui.info.state

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.domain.Team
import com.mss.core.ui.utils.SocialLinkMapper

data class TeamInfoModelState(
    val teamInfo: Team? = null,
    val series: List<SeriesWithDrivers> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null,
) {
    fun toUiState() = TeamInfoUiState(
        name = teamInfo?.name.orEmpty(),
        picture = teamInfo?.picture,
        constructors = teamInfo?.constructors?.map { it.name }.orEmpty(),
        firstSeries = series.firstOrNull(),
        otherSeries = series.drop(1),
        isLoading = isLoading,
        errorMessageId = errorMessageId,
        hasData = teamInfo != null,
        links = teamInfo?.resourceLinks?.map(SocialLinkMapper).orEmpty()
    )
}