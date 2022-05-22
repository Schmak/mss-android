package com.mss.core.domain.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeasonDriverOrder.ChampionshipPosition
import com.mss.core.utils.Mapper
import com.mss.network.api.SeasonApi

object SeasonDriverOrderMapper : Mapper<DriverRepository.SeasonDriverOrder, SeasonApi.DriverOrder> {
    override fun DriverRepository.SeasonDriverOrder.map() = when (this) {
        ChampionshipPosition -> SeasonApi.DriverOrder.ChampionshipPosition
    }
}