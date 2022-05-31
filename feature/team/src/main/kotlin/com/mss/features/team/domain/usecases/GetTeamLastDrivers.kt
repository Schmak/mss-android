package com.mss.features.team.domain.usecases

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetTeamLastDrivers @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamSlug: String): Result<List<SeriesWithDrivers>> =
        repository.getLastDrivers(teamSlug)
}