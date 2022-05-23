package com.mss.core.network.v3.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeasonDriverOrder.ChampionshipPosition
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.utils.Mapper

object SeasonDriverOrderMapper :
    Mapper<DriverRepository.SeasonDriverOrder, SeasonApiV3.DriverOrder> {
    override fun DriverRepository.SeasonDriverOrder.map() = when (this) {
        ChampionshipPosition -> SeasonApiV3.DriverOrder.ChampionshipPosition
    }
}