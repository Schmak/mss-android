package com.mss.core.network.v4.mapper

import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.Collection.ChampionshipLeaders
import com.mss.core.domain.repository.TeamRepository.Collection.RecentWinners
import com.mss.core.network.v4.api.TeamApiV4.TeamCollection
import com.mss.core.utils.Mapper

object TeamCollectionMapper : Mapper<TeamRepository.Collection, TeamCollection> {
    override fun TeamRepository.Collection.map(): TeamCollection = when (this) {
        RecentWinners -> TeamCollection.RecentWinners
        ChampionshipLeaders -> TeamCollection.ChampionshipLeaders
    }
}