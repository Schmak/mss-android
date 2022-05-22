package com.mss.network.model.ref

fun TeamReferenceDto.Companion.create(
    name: String = "Team name",
    uuid: String = "Team uuid",
    code: String? = "Team code",
    picture: String? = "Team picture",
) = TeamReferenceDto(
    name = name,
    uuid = uuid,
    code = code,
    picture = picture,
)