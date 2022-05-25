package com.mss.features.series.domain.usecases

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.utils.Result
import javax.inject.Inject

class GetLastChampions @Inject constructor(
    private val repository: SeriesRepository,
) {
    suspend operator fun invoke(seriesSlug: String): Result<LastSeriesChampions?> =
        repository.getLastChampions(seriesSlug)
}