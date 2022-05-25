package com.mss.features.series.presentation.ui.info.state

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.ui.model.SocialLink

data class SeriesInfoUiState(
    val seriesInfo: Series,
    val lastChampions: LastSeriesChampions?,
    val links: List<SocialLink>,
)