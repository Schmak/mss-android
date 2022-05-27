package com.mss.core.domain.usecases

import com.mss.core.domain.common.ResourceLink
import javax.inject.Inject

class FilterAndOrderResourceLinks @Inject constructor() {
    operator fun invoke(links: List<ResourceLink>): List<ResourceLink> =
        listOfNotNull(
            links.firstOrNull { it is ResourceLink.Twitter },
            links.firstOrNull { it is ResourceLink.Facebook },
            links.firstOrNull { it is ResourceLink.Instagram },
            links.firstOrNull { it is ResourceLink.YouTube },
        )
}