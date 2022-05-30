package com.mss.core.network.v4.mapper

import com.mss.core.domain.SeriesWithTeam
import com.mss.core.network.v4.mapper.ref.SeriesReferenceMapper
import com.mss.core.network.v4.mapper.ref.TeamReferenceMapper
import com.mss.core.network.v4.model.SeriesWithTeamDto
import com.mss.core.utils.Mapper

object SeriesWithTeamMapper : Mapper<SeriesWithTeamDto, SeriesWithTeam> {
    override fun SeriesWithTeamDto.map() = SeriesWithTeam(
        series = series.let(SeriesReferenceMapper),
        team = team?.let(TeamReferenceMapper),
    )
}