package com.mss.features.driver.domain.usecases

import com.mss.core.domain.Driver
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.usecases.FilterAndOrderResourceLinks
import com.mss.core.utils.Result
import com.mss.core.utils.Result.Companion.map
import javax.inject.Inject

class GetDriverInfo @Inject constructor(
    private val repository: DriverRepository,
    private val filterAndOrderResourceLinks: FilterAndOrderResourceLinks,
) {
    suspend operator fun invoke(driverSlug: String): Result<Driver> =
        repository.getInfo(driverSlug)
            .map { it.copy(resourceLinks = filterAndOrderResourceLinks(it.resourceLinks)) }
}