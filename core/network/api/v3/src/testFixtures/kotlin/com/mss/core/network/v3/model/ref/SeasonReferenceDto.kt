package com.mss.core.network.v3.model.ref

fun SeasonReferenceDto.Companion.create(
    name: String = "Season name",
    uuid: String = "Season uuid",
) = SeasonReferenceDto(
    name = name,
    uuid = uuid,
)