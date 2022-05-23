package com.mss.core.network.v3.mapper

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.network.v3.api.DriverApi
import com.mss.core.utils.Mapper

object DriverCollectionMapper : Mapper<DriverRepository.Collection, DriverApi.DriverCollection> {
    override fun DriverRepository.Collection.map(): DriverApi.DriverCollection = when (this) {
        DriverRepository.Collection.Anniversaries -> DriverApi.DriverCollection.Anniversaries
        DriverRepository.Collection.Debutants -> DriverApi.DriverCollection.Debutants
        DriverRepository.Collection.RecentWinners -> DriverApi.DriverCollection.RecentWinners
        DriverRepository.Collection.ChampionshipLeaders -> DriverApi.DriverCollection.ChampionshipLeaders
    }
}