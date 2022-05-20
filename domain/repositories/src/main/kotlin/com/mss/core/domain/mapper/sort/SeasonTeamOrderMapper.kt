package com.mss.core.domain.mapper.sort

import com.mss.core.domain.mapper.sort.common.OrderByMapper
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.SeasonTeamOrder.ChampionshipPosition
import com.mss.core.utils.Mapper
import com.mss.network.api.SeasonApi

object SeasonTeamOrderMapper : Mapper<TeamRepository.SeasonTeamOrder, SeasonApi.TeamOrder> {
    val orderByMapper = OrderByMapper(this)

    override fun TeamRepository.SeasonTeamOrder.map() = when (this) {
        ChampionshipPosition -> SeasonApi.TeamOrder.ChampionshipPosition
    }
}