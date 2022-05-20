package com.mss.features.series.domain.usecases

import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetSeriesRegions @Inject constructor(
    private val repository: SeriesRepository,
) {
    suspend operator fun invoke(): Result<List<String>> = repository.getRegions()
}