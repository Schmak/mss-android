package com.mss.features.series.presentation.ui.info.state

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.ui.model.SocialLink
import com.mss.core.ui.model.common.UiState

data class SeriesInfoUiState(
    val seriesInfo: Series?,
    val lastChampions: LastSeriesChampions?,
    val links: List<SocialLink>,
    override val isLoading: Boolean,
    override val errorMessageId: Int?,
    override val hasData: Boolean,
) : UiState