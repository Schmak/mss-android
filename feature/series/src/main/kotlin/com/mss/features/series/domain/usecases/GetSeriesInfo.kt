package com.mss.features.series.domain.usecases

import com.mss.core.domain.Series
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.domain.usecases.FilterAndOrderResourceLinks
import com.mss.core.utils.Result
import com.mss.core.utils.Result.Companion.map
import javax.inject.Inject

class GetSeriesInfo @Inject constructor(
    private val repository: SeriesRepository,
    private val filterAndOrderResourceLinks: FilterAndOrderResourceLinks,
) {
    suspend operator fun invoke(seriesSlug: String): Result<Series> =
        repository.getSeriesInfo(seriesSlug)
            .map { it.copy(links = filterAndOrderResourceLinks(it.links)) }
}