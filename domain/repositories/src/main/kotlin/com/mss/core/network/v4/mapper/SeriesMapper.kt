package com.mss.core.network.v4.mapper

import com.mss.core.domain.Series
import com.mss.core.network.v4.mapper.common.ResourceLinkMapper
import com.mss.core.network.v4.model.SeriesDto
import com.mss.core.utils.Mapper

object SeriesMapper : Mapper<SeriesDto, Series> {
    override fun SeriesDto.map() = Series(
        name = name,
        shortName = shortName,
        picture = picture,
        firstSeason = firstSeason,
        category = template.seriesCategory,
        organisation = organisation?.name,
        links = resourceLinks.mapNotNull(ResourceLinkMapper)
    )
}