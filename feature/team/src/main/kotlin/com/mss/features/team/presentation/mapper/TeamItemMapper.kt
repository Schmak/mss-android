package com.mss.features.team.presentation.mapper

import com.mss.core.domain.TeamItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper

object TeamItemMapper : Mapper<TeamItem, UiItem> {
    override fun TeamItem.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitle = country?.name.orEmpty(),
        )
}