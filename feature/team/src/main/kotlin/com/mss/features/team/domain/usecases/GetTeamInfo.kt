package com.mss.features.team.domain.usecases

import com.mss.core.domain.Team
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetTeamInfo @Inject constructor(
    private val repository: TeamRepository,
) {
    suspend operator fun invoke(teamSlug: String): Result<Team> =
        repository.getInfo(teamSlug)
}