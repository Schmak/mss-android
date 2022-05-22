package com.mss.core.domain.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.ChampionshipWins
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.Wins
import com.mss.core.utils.Mapper
import com.mss.network.api.SeriesApi

object SeriesDriverOrderMapper : Mapper<DriverRepository.SeriesDriverOrder, SeriesApi.DriverOrder> {

    override fun DriverRepository.SeriesDriverOrder.map() = when (this) {
        ChampionshipWins -> SeriesApi.DriverOrder.ChampionshipWins
        Wins -> SeriesApi.DriverOrder.Wins
    }
}