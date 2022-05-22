package com.mss.network.model.ref

fun EventReferenceDto.Companion.create(
    name: String = "Event name",
    uuid: String = "Event uuid",
    code: String? = "Event code",
) = EventReferenceDto(
    name = name,
    uuid = uuid,
    code = code,
)