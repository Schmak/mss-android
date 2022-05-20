package com.mss.core.domain.mapper

import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.Collection.ChampionshipLeaders
import com.mss.core.domain.repository.TeamRepository.Collection.RecentWinners
import com.mss.core.utils.Mapper
import com.mss.network.api.TeamApi

object TeamCollectionMapper : Mapper<TeamRepository.Collection, TeamApi.TeamCollection> {
    override fun TeamRepository.Collection.map(): TeamApi.TeamCollection = when (this) {
        RecentWinners -> TeamApi.TeamCollection.RecentWinners
        ChampionshipLeaders -> TeamApi.TeamCollection.ChampionshipLeaders
    }
}