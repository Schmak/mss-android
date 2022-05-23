package com.mss.core.network.v3.model.ref

fun SeriesReferenceDto.Companion.create(
    name: String = "Series name",
    shortName: String? = "Series shortname",
    uuid: String = "Series uuid",
    code: String? = "Series code",
    picture: String? = "Series picture",
) = SeriesReferenceDto(
    name = name,
    shortName = shortName,
    uuid = uuid,
    code = code,
    picture = picture,
)