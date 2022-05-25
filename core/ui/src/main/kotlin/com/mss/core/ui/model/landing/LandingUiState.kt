package com.mss.core.ui.model.landing

import androidx.annotation.StringRes

data class LandingUiState(
    @StringRes val titleId: Int,
    val blocks: List<LandingBlockState> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val errorMessageId: Int? = null,
    val hasData: Boolean = false,
)