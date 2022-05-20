package com.mss.core.domain.mapper

import com.mss.core.domain.TeamItem
import com.mss.core.domain.mapper.ref.CountryReferenceMapper
import com.mss.core.utils.Mapper
import com.mss.network.model.TeamItemDto

object TeamItemMapper : Mapper<TeamItemDto, TeamItem> {
    val pageMapper = PageMapper(TeamItemMapper)

    override fun TeamItemDto.map() = TeamItem(
        slug = slug,
        name = name,
        picture = picture,
        country = country?.let(CountryReferenceMapper),
    )
}