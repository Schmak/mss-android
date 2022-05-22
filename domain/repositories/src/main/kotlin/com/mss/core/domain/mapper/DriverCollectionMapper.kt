package com.mss.core.domain.mapper

import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.Collection.*
import com.mss.core.utils.Mapper
import com.mss.network.api.DriverApi

object DriverCollectionMapper : Mapper<DriverRepository.Collection, DriverApi.DriverCollection> {
    override fun DriverRepository.Collection.map(): DriverApi.DriverCollection = when (this) {
        Anniversaries -> DriverApi.DriverCollection.Anniversaries
        Debutants -> DriverApi.DriverCollection.Debutants
        RecentWinners -> DriverApi.DriverCollection.RecentWinners
        ChampionshipLeaders -> DriverApi.DriverCollection.ChampionshipLeaders
    }
}