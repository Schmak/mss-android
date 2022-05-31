package com.mss.core.domain.ref

fun ConstructorReference.Companion.create(
    name: String = "constructor name",
) = ConstructorReference(
    name = name
)