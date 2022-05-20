package com.mss.core.ui.model

import androidx.annotation.StringRes
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class LandingBlockState(
    @StringRes val titleId: Int,
    val selector: Selector? = null,
    val itemsFlow: Flow<PagingData<UiItem>>,
    val itemsConfig: UiItem.Configuration = UiItem.Configuration.Default,
) {
    data class Selector(
        val id: Any? = null,
        @StringRes val titleId: Int,
        val values: List<String>,
        val selectedIdx: Int = 0,
    )
}