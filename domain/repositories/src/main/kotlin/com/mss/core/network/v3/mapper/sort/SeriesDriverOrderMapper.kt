package com.mss.core.network.v3.mapper.sort

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.ChampionshipWins
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.Wins
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.utils.Mapper

object SeriesDriverOrderMapper :
    Mapper<DriverRepository.SeriesDriverOrder, SeriesApiV3.DriverOrder> {

    override fun DriverRepository.SeriesDriverOrder.map() = when (this) {
        ChampionshipWins -> SeriesApiV3.DriverOrder.ChampionshipWins
        Wins -> SeriesApiV3.DriverOrder.Wins
    }
}