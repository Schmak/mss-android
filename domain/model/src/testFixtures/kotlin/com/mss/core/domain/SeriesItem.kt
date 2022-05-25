package com.mss.core.domain

import com.mss.core.test.utils.DEFAULT_LOCAL_DATE
import java.time.LocalDate

fun SeriesItem.Companion.create(
    slug: String = "Series slug",
    name: String = "Series name",
    picture: String? = "Series picture",
    region: String? = "Series region",
    lastEvent: SeriesItem.LastEvent? = SeriesItem.LastEvent.create(),
) = SeriesItem(
    slug = slug,
    name = name,
    picture = picture,
    region = region,
    lastEvent = lastEvent,
)

fun SeriesItem.LastEvent.Companion.create(
    name: String = "Event name",
    date: LocalDate = DEFAULT_LOCAL_DATE,
) = SeriesItem.LastEvent(
    name = name,
    date = date,
)
