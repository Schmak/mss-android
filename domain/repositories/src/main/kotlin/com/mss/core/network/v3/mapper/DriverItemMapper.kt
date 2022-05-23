package com.mss.core.network.v3.mapper

import com.mss.core.domain.DriverItem
import com.mss.core.network.v3.mapper.ref.CountryReferenceMapper
import com.mss.core.network.v3.mapper.ref.TeamReferenceMapper
import com.mss.core.network.v3.model.DriverItemDto
import com.mss.core.utils.Mapper

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