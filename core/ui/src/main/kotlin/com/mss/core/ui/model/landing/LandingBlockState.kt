package com.mss.core.ui.model.landing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.mss.core.ui.model.landing.UiItemConfiguration.Default
import kotlinx.coroutines.flow.Flow

data class LandingBlockState(
    @StringRes val titleId: Int,
    val selector: Selector? = null,
    val action: Action? = null,
    val itemsFlow: Flow<PagingData<UiItem>>,
    val itemsConfig: UiItemConfiguration = Default,
) {
    data class Selector(
        val id: Any? = null,
        @StringRes val titleId: Int,
        val values: List<String>,
        val selectedIdx: Int = 0,
    )

    data class Action(
        val id: Any? = null,
        @DrawableRes val drawableId: Int,
        @StringRes val descriptionId: Int,
    )
}