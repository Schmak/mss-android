package com.mss.core.network.v4.mapper

import com.mss.core.domain.TeamItem
import com.mss.core.network.v4.mapper.ref.CountryReferenceMapper
import com.mss.core.network.v4.model.TeamItemDto
import com.mss.core.utils.Mapper

object TeamItemMapper : Mapper<TeamItemDto, TeamItem> {
    val pageMapper = PageMapper(TeamItemMapper)

    override fun TeamItemDto.map() = TeamItem(
        slug = slug,
        name = name,
        picture = picture,
        country = country?.let(CountryReferenceMapper),
    )
}