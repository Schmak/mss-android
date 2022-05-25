package com.mss.core.network.v4.mapper

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.network.v4.mapper.ref.DriverReferenceMapper
import com.mss.core.network.v4.mapper.ref.TeamReferenceMapper
import com.mss.core.network.v4.model.LastSeriesChampionsDto
import com.mss.core.utils.Mapper

object LastSeriesChampionsMapper : Mapper<LastSeriesChampionsDto, LastSeriesChampions> {
    override fun LastSeriesChampionsDto.map() =
        LastSeriesChampions(
            drivers = drivers.map { it.driver }.map(DriverReferenceMapper),
            team = team?.let(TeamReferenceMapper)
        )
}