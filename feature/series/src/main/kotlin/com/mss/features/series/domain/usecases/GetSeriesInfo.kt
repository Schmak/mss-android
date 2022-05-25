package com.mss.features.series.domain.usecases

import com.mss.core.domain.Series
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.utils.Result
import com.mss.core.utils.Result.Companion.map
import javax.inject.Inject

/**
 * Returns SeriesInfo with reordered ResourceLinks and only one instance for each type of link.
 * */
class GetSeriesInfo @Inject constructor(
    private val repository: SeriesRepository,
) {
    suspend operator fun invoke(seriesSlug: String): Result<Series> =
        repository.getSeriesInfo(seriesSlug)
            .map { series ->
                series.copy(
                    links = listOfNotNull(
                        series.links.firstOrNull { it is ResourceLink.Twitter },
                        series.links.firstOrNull { it is ResourceLink.Facebook },
                        series.links.firstOrNull { it is ResourceLink.Instagram },
                        series.links.firstOrNull { it is ResourceLink.YouTube },
                    )
                )
            }
}