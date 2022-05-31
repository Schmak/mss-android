package com.mss.core.network.v4.mapper

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.network.v4.mapper.ref.DriverReferenceMapper
import com.mss.core.network.v4.mapper.ref.SeriesReferenceMapper
import com.mss.core.network.v4.model.SeriesWithDriversDto
import com.mss.core.utils.Mapper

object SeriesWithDriversMapper : Mapper<SeriesWithDriversDto, SeriesWithDrivers> {
    override fun SeriesWithDriversDto.map() = SeriesWithDrivers(
        series = series.let(SeriesReferenceMapper),
        drivers = drivers.map(DriverReferenceMapper),
    )
}