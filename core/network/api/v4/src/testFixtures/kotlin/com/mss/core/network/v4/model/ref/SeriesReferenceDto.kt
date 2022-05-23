package com.mss.core.network.v4.model.ref

fun SeriesReferenceDto.Companion.create(
    name: String = "series name",
    shortName: String? = "series shortName",
    code: String? = "series code",
    slug: String = "series-slug",
    uuid: String = "series uuid",
    picture: String? = "series picture",
) = SeriesReferenceDto(
    name = name,
    shortName = shortName,
    code = code,
    slug = slug,
    uuid = uuid,
    picture = picture
)