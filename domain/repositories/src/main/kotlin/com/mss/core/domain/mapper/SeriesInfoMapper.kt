package com.mss.core.domain.mapper

import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.mapper.ref.SeasonReferenceMapper
import com.mss.core.domain.mapper.ref.SeriesReferenceMapper
import com.mss.core.utils.Mapper
import com.mss.network.model.SeriesInfoDto

object SeriesInfoMapper : Mapper<SeriesInfoDto, SeriesInfo> {
    override fun SeriesInfoDto.map() = SeriesInfo(
        series = series.let(SeriesReferenceMapper),
        currentSeason = currentSeason?.let(SeasonReferenceMapper),
    )
}