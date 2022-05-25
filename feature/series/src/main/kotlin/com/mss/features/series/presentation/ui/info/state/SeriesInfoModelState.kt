package com.mss.features.series.presentation.ui.info.state

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.domain.common.ResourceLink
import com.mss.core.ui.model.SocialLink
import com.mss.features.series.R

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
        hasData = seriesInfo != null && lastChampions != null,
        links = seriesInfo?.links?.map {
            when (it) {
                is ResourceLink.Twitter -> SocialLink(R.drawable.ic_twitter, it.url)
                is ResourceLink.Facebook -> SocialLink(R.drawable.ic_facebook, it.url)
                is ResourceLink.Instagram -> SocialLink(R.drawable.ic_instagram, it.url)
                is ResourceLink.YouTube -> SocialLink(R.drawable.ic_youtube, it.url)
            }
        }.orEmpty()
    )
}