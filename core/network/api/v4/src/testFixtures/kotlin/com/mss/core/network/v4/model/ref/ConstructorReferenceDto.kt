package com.mss.core.network.v4.model.ref

fun ConstructorReferenceDto.Companion.create(
    name: String = "constructor name",
) = ConstructorReferenceDto(
    name = name
)