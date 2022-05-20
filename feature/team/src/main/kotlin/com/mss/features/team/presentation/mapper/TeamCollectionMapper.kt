package com.mss.features.team.presentation.mapper

import com.mss.core.domain.repository.TeamRepository
import com.mss.core.utils.Mapper
import com.mss.features.team.presentation.ui.landing.Collections
import com.mss.features.team.presentation.ui.landing.Collections.RecentWinners
import com.mss.features.team.presentation.ui.landing.Collections.SeriesLeaders

object TeamCollectionMapper : Mapper<Collections, TeamRepository.Collection> {
    override fun Collections.map(): TeamRepository.Collection = when (this) {
        RecentWinners -> TeamRepository.Collection.RecentWinners
        SeriesLeaders -> TeamRepository.Collection.ChampionshipLeaders
    }
}