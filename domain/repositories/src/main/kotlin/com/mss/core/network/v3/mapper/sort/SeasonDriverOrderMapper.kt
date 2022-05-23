package com.mss.core.network.v3.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeasonDriverOrder.ChampionshipPosition
import com.mss.core.network.v3.api.SeasonApi
import com.mss.core.utils.Mapper

object SeasonDriverOrderMapper : Mapper<DriverRepository.SeasonDriverOrder, SeasonApi.DriverOrder> {
    override fun DriverRepository.SeasonDriverOrder.map() = when (this) {
        ChampionshipPosition -> SeasonApi.DriverOrder.ChampionshipPosition
    }
}