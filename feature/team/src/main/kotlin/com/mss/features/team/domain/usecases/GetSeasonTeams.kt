package com.mss.features.team.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.TeamItem
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.TeamRepository.SeasonTeamOrder.ChampionshipPosition
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeasonTeams @Inject constructor(
    private val repository: TeamRepository,
) {
    operator fun invoke(season: String, pageSize: Int = 10): Flow<PagingData<TeamItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getSeasonTeams(
                    season = season,
                    orderBy = ChampionshipPosition.asc,
                    pageable = it
                )
            }
        }.flow
}