package com.mss.features.team.domain.usecases

import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetGoldenSeries @Inject constructor(
    private val seriesRepository: SeriesRepository,
) {
    suspend operator fun invoke(): Result<List<SeriesInfo>> = seriesRepository.getLeadingSeries()
}