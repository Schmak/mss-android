package com.mss.network.model.ref

fun SeasonReferenceDto.Companion.create(
    name: String = "Season name",
    uuid: String = "Season uuid",
) = SeasonReferenceDto(
    name = name,
    uuid = uuid,
)