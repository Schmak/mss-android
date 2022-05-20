package com.mss.core.domain.mapper.sort

import com.mss.core.domain.mapper.sort.common.OrderByMapper
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.SeriesTeamOrder.ChampionshipWins
import com.mss.core.domain.repository.TeamRepository.SeriesTeamOrder.TeamWins
import com.mss.core.utils.Mapper
import com.mss.network.api.SeriesApi

object SeriesTeamOrderMapper : Mapper<TeamRepository.SeriesTeamOrder, SeriesApi.TeamOrder> {
    val orderByMapper = OrderByMapper(this)

    override fun TeamRepository.SeriesTeamOrder.map() = when (this) {
        ChampionshipWins -> SeriesApi.TeamOrder.ChampionshipWins
        TeamWins -> SeriesApi.TeamOrder.TeamWins
    }
}