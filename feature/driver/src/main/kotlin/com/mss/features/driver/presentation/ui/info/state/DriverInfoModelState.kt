package com.mss.features.driver.presentation.ui.info.state

import com.mss.core.domain.Driver
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.TeamReference
import com.mss.core.ui.utils.SocialLinkMapper

data class DriverInfoModelState(
    val driverInfo: Driver? = null,
    val series: List<SeriesReference> = emptyList(),
    val teams: List<TeamReference> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null,
) {
    fun toUiState() = DriverInfoUiState(
        driver = driverInfo,
        series = series.map { it.name },
        teams = teams.map { it.name },
        isLoading = isLoading,
        errorMessageId = errorMessageId,
        hasData = driverInfo != null,
        links = driverInfo?.resourceLinks?.map(SocialLinkMapper).orEmpty()
    )
}