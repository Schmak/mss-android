package com.mss.core.ui.data.mock

import com.mss.core.domain.ref.EventReference
import kotlin.random.Random

@Suppress("MagicNumber")
object MockEventData {
    private val random = Random(1234)

    val names = listOf(
        "Road America", "106th Running of the Indianapolis 500", "Donington (National)",
        "Estoril Round", "Circuit Paul Ricard", "Spanish Grand Prix", "Lausitzring Turn 1",
        "55. Vodafone Rally de Portugal", "Red Bull Ring", "Barcelona", "Winton SuperSprint",
        "Autopolis", "SRS Distribution 250", "SpeedyCash.com 220", "AdventHealth 400",
        "Lexus Grand Prix at Mid-Ohio", "Brands Hatch Indy",
    )

    val references = List(10) {
        EventReference(
            name = names.random(random),
            slug = "event.slug",
        )
    }
}