package com.mss.features.driver.presentation.mapper

import com.mss.core.domain.DriverItem
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.utils.Mapper

object DriverItemMapper : Mapper<DriverItem, UiItem> {
    override fun DriverItem.map() =
        SimpleUiItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitles = listOf(
                lastTeam?.name,
                nationality?.name
            ),
        )
}