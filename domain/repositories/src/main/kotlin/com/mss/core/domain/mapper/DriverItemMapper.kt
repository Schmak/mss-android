package com.mss.core.domain.mapper

import com.mss.core.domain.DriverItem
import com.mss.core.domain.mapper.ref.CountryReferenceMapper
import com.mss.core.domain.mapper.ref.TeamReferenceMapper
import com.mss.core.utils.Mapper
import com.mss.network.model.DriverItemDto

object DriverItemMapper : Mapper<DriverItemDto, DriverItem> {
    val pageMapper = PageMapper(DriverItemMapper)

    override fun DriverItemDto.map() = DriverItem(
        slug = uuid,
        name = name,
        picture = picture,
        lastTeam = lastTeam?.let(TeamReferenceMapper),
        nationality = nationalities.firstOrNull()?.let(CountryReferenceMapper)
    )
}