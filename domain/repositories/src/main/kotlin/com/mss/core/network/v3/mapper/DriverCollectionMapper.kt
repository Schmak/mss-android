package com.mss.core.network.v3.mapper

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.utils.Mapper

object DriverCollectionMapper : Mapper<DriverRepository.Collection, DriverApiV3.DriverCollection> {
    override fun DriverRepository.Collection.map(): DriverApiV3.DriverCollection = when (this) {
        DriverRepository.Collection.Anniversaries -> DriverApiV3.DriverCollection.Anniversaries
        DriverRepository.Collection.Debutants -> DriverApiV3.DriverCollection.Debutants
        DriverRepository.Collection.RecentWinners -> DriverApiV3.DriverCollection.RecentWinners
        DriverRepository.Collection.ChampionshipLeaders -> DriverApiV3.DriverCollection.ChampionshipLeaders
    }
}