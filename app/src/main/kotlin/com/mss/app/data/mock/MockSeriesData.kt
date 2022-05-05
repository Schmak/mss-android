package com.mss.app.data.mock

import com.mss.app.model.SeriesItem
import kotlin.random.Random

@Suppress("MagicNumber")
object MockSeriesData {
    private val random = Random(12_345)

    private val pictures = listOf(
        "a33f8b4a-2b22-41ce-8e7d-0aea08f0e176", "a485d01c-f907-4ff7-83db-ca1c90cc28a1",
        "10904579-6e07-4a99-9b1f-19725110ceef", "7415d1ab-c31d-49dd-9143-1e7e33bff889",
        "967cd5ab-5562-40dc-a0b0-109738adcd01", "0f0f963a-d489-4b9a-8945-71d99bfabd62",
        "220b82cb-7fdb-46df-b8f8-d180118aa605", "100ef87b-99ad-478d-aeef-1dd4038379be",
        "error"
    ).map { "https://content.motorsportstats.com/seriesProfilePicture/seriesProfilePicture-$it.png" }

    private val names = listOf(
        "Formula One", "MotoGP", "World Rally Championship", "Formula E", "World Endurance Championship",
        "NASCAR Cup Series", "Indy Car Series", "World Rallycross Championship", "FIA World Touring Car Cup",
        "Deutsche Tourenwagen Masters", "Super GT", "British Superbike Championship",
        "GT World Challenge Europe Sprint Cup",
    )

    val regions = listOf(
        "Worldwide", "Europe", "North America", "Asia", "Australasia", "France", "Germany", "Great Britain",
        "Japan", "Macau", "United States of America"
    )

    val leadingSeries = List(10) {
        SeriesItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
        )
    }

    val regionSeries = List(10) {
        SeriesItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitle = regions.random(random),
        )
    }
}