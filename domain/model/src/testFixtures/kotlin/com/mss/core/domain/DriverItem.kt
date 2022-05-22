package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.TeamReference
import com.mss.core.domain.ref.create

fun DriverItem.Companion.create(
    slug: String = "driver-slug",
    name: String = "driver name",
    picture: String? = "driver picture",
    lastTeam: TeamReference? = TeamReference.create(),
    nationality: CountryReference? = CountryReference.create(),
) = DriverItem(
    slug = slug,
    name = name,
    picture = picture,
    lastTeam = lastTeam,
    nationality = nationality
)