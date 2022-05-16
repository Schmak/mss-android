package com.mss.features.series.domain.usecases

import com.mss.core.utils.Result
import com.mss.features.series.domain.repository.SeriesRepository
import javax.inject.Inject

class GetSeriesCategories @Inject constructor(
    private val repository: SeriesRepository,
) {
    suspend operator fun invoke(): Result<List<String>> = repository.getCategories()
}