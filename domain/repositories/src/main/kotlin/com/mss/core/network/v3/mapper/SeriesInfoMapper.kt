package com.mss.core.network.v3.mapper

import com.mss.core.domain.SeriesInfo
import com.mss.core.network.v3.mapper.ref.SeasonReferenceMapper
import com.mss.core.network.v3.mapper.ref.SeriesReferenceMapper
import com.mss.core.network.v3.model.SeriesInfoDto
import com.mss.core.utils.Mapper

object SeriesInfoMapper : Mapper<SeriesInfoDto, SeriesInfo> {
    override fun SeriesInfoDto.map() = SeriesInfo(
        series = series.let(SeriesReferenceMapper),
        currentSeason = currentSeason?.let(SeasonReferenceMapper),
    )
}