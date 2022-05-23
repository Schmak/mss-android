package com.mss.core.network.v3.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.ChampionshipWins
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.Wins
import com.mss.core.network.v3.api.SeriesApi
import com.mss.core.utils.Mapper

object SeriesDriverOrderMapper : Mapper<DriverRepository.SeriesDriverOrder, SeriesApi.DriverOrder> {

    override fun DriverRepository.SeriesDriverOrder.map() = when (this) {
        ChampionshipWins -> SeriesApi.DriverOrder.ChampionshipWins
        Wins -> SeriesApi.DriverOrder.Wins
    }
}