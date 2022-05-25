package com.mss.core.ui.model.landing

import androidx.annotation.StringRes
import com.mss.core.ui.model.common.UiState

data class LandingUiState(
    @StringRes val titleId: Int,
    val blocks: List<LandingBlockState> = emptyList(),
    override val isLoading: Boolean = false,
    @StringRes override val errorMessageId: Int? = null,
    override val hasData: Boolean = false,
) : UiState