package com.mss.core.network.v4.mapper.sort

import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.SeasonTeamOrder.ChampionshipPosition
import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.mapper.sort.common.OrderByMapper
import com.mss.core.utils.Mapper

object SeasonTeamOrderMapper : Mapper<TeamRepository.SeasonTeamOrder, SeasonApiV4.TeamOrder> {
    val orderByMapper = OrderByMapper(this)

    override fun TeamRepository.SeasonTeamOrder.map() = when (this) {
        ChampionshipPosition -> SeasonApiV4.TeamOrder.ChampionshipPosition
    }
}