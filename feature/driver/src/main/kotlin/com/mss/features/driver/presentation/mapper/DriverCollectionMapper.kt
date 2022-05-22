package com.mss.features.driver.presentation.mapper

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.utils.Mapper
import com.mss.features.driver.presentation.ui.landing.Collections
import com.mss.features.driver.presentation.ui.landing.Collections.*

object DriverCollectionMapper : Mapper<Collections, DriverRepository.Collection> {
    override fun Collections.map(): DriverRepository.Collection = when (this) {
        Anniversaries -> DriverRepository.Collection.Anniversaries
        ChampionshipLeaders -> DriverRepository.Collection.ChampionshipLeaders
        Debutants -> DriverRepository.Collection.Debutants
        RecentWinners -> DriverRepository.Collection.RecentWinners
    }
}