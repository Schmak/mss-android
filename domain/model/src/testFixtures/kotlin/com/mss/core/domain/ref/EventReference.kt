package com.mss.core.domain.ref

fun EventReference.Companion.create(
    name: String = "event name",
    slug: String = "event-slug",
) = EventReference(
    name = name,
    slug = slug
)