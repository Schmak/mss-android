package com.mss.core.network.v3.model.ref

fun DriverReferenceDto.Companion.create(
    name: String = "driver name",
    uuid: String = "driver uuid",
    code: String? = "driver code",
    picture: String? = "driver picture",
) = DriverReferenceDto(
    name = name,
    uuid = uuid,
    code = code,
    picture = picture
)