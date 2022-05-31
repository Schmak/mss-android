package com.mss.features.driver.domain.usecases

import com.mss.core.domain.SeriesWithTeam
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetDriverLastTeams @Inject constructor(
    private val repository: DriverRepository,
) {
    suspend operator fun invoke(driverSlug: String): Result<List<SeriesWithTeam>> =
        repository.getLastTeams(driverSlug)
}