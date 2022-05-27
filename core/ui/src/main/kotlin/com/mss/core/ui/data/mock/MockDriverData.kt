package com.mss.core.ui.data.mock

import com.mss.core.domain.ref.DriverReference
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import kotlin.random.Random

@Suppress("MagicNumber")
object MockDriverData {
    private val random = Random(12_345)

    private val pictures = listOf(
        "2a6c4196-ab5f-4850-bac2-03ff42ae6a34", "458bf697-7fb6-41cd-bbbe-15a6b39ed4e3",
        "8d5486d9-c622-4961-9f6e-9bb5b3ed14e1", "cfaa9e76-9976-474e-8fc3-2ac9637708e2",
        "c2d31132-e951-4332-8da5-537be5951073", "985d7eec-3c7b-4153-a189-1dd1b72538cc",
        "8f039358-b9b5-4bb3-b1cf-b4e60cd1a67a", "12fbcf18-2d78-4fc5-a68a-07cfa881dbc2",
        "940151ca-355f-4305-b4da-577cd1b0ec52", "3305223c-dab1-4a0a-b818-660565378791",
        "error"
    ).map { "https://content.motorsportstats.com/driverProfilePicture/driverProfilePicture-$it.jpg" }

    private val names = listOf(
        "Alexander Albon", "Carlos Sainz", "Charles Leclerc", "Daniel Ricciardo", "Esteban Ocon",
        "Fernando Alonso", "George Russell", "Jüri Vips", "Kevin Magnussen", "Lance Stroll",
    )

    val currentSeasonDrivers = getDriverList()
    val champions = getDriverList()
    val winners = getDriverList()
    val collectionDrivers = getDriverList()
    val driverReferences = List(10) {
        DriverReference(
            name = names.random(random),
            slug = "",
            picture = pictures.random(random),
            countryFlag = MockCountryData.flags.random(random),
        )
    }

    private fun getDriverList(size: Int = 10) = List<UiItem>(size) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitles = listOf(MockTeamData.names.random(), MockCountryData.names.random()),
        )
    }
}