package com.mss.core.network.v4.mapper.sort

import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.SeriesTeamOrder.ChampionshipWins
import com.mss.core.domain.repository.TeamRepository.SeriesTeamOrder.TeamWins
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.mapper.sort.common.OrderByMapper
import com.mss.core.utils.Mapper

object SeriesTeamOrderMapper : Mapper<TeamRepository.SeriesTeamOrder, SeriesApiV4.TeamOrder> {
    val orderByMapper = OrderByMapper(this)

    override fun TeamRepository.SeriesTeamOrder.map() = when (this) {
        ChampionshipWins -> SeriesApiV4.TeamOrder.ChampionshipWins
        TeamWins -> SeriesApiV4.TeamOrder.TeamWins
    }
}