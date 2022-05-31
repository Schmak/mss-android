package com.mss.features.series.presentation.ui.info.state

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.ui.utils.SocialLinkMapper

data class SeriesInfoModelState(
    val seriesInfo: Series? = null,
    val lastChampions: LastSeriesChampions? = null,
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null,
) {
    fun toUiState() = SeriesInfoUiState(
        seriesInfo = seriesInfo,
        lastChampions = lastChampions,
        isLoading = isLoading,
        errorMessageId = errorMessageId,
        hasData = seriesInfo != null,
        links = seriesInfo?.links?.map(SocialLinkMapper).orEmpty()
    )
}