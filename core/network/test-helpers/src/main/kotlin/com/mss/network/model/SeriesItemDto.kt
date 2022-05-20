package com.mss.network.model

import com.mss.network.model.ref.EventReferenceDto
import com.mss.network.model.ref.create

fun SeriesItemDto.Companion.create(
    uuid: String = "Series uuid",
    name: String = "Series name",
    code: String = "Series code",
    picture: String? = "Series picture",
    region: String = "Series region",
    category: String = "Series category",
    firstFullSeasonYear: String? = "Series firstFullSeasonYear",
    lastEvent: EventReferenceDto = EventReferenceDto.create(),
    lastEventDate: Long? = 1_234L,
) = SeriesItemDto(
    uuid = uuid,
    name = name,
    code = code,
    picture = picture,
    region = region,
    category = category,
    firstFullSeasonYear = firstFullSeasonYear,
    lastEvent = lastEvent,
    lastEventDate = lastEventDate
)