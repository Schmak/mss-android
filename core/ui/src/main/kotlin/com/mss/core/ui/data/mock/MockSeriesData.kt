package com.mss.core.ui.data.mock

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.ref.SeasonReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.ui.model.SimpleUiItem
import com.mss.core.ui.model.UiItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

@Suppress("MagicNumber")
object MockSeriesData {
    private val random = Random(12_345)

    val pictures = listOf(
        "a33f8b4a-2b22-41ce-8e7d-0aea08f0e176", "a485d01c-f907-4ff7-83db-ca1c90cc28a1",
        "10904579-6e07-4a99-9b1f-19725110ceef", "7415d1ab-c31d-49dd-9143-1e7e33bff889",
        "967cd5ab-5562-40dc-a0b0-109738adcd01", "0f0f963a-d489-4b9a-8945-71d99bfabd62",
        "220b82cb-7fdb-46df-b8f8-d180118aa605", "100ef87b-99ad-478d-aeef-1dd4038379be",
        "error"
    ).map { "https://content.motorsportstats.com/seriesProfilePicture/seriesProfilePicture-$it.png" }

    private val names = listOf(
        "Formula One",
        "MotoGP",
        "World Rally Championship",
        "Formula E",
        "World Endurance Championship",
        "NASCAR Cup Series",
        "Indy Car Series",
        "World Rallycross Championship",
        "FIA World Touring Car Cup",
        "Deutsche Tourenwagen Masters",
        "Super GT",
        "British Superbike Championship",
        "GT World Challenge Europe Sprint Cup",
    )

    val goldenSeries = names.take(10).map { name ->
        SeriesInfo(
            series = SeriesReference(
                slug = "",
                name = name,
                picture = null
            ),
            currentSeason = SeasonReference(
                slug = "",
                name = "2022 $name",
            )
        )
    }

    private val eventNames = listOf(
        "Fuji",
        "Oulton Park",
        "Portimão",
        "Estoril",
        "Spain",
        "Brands Hatch",
        "Hyundai Monterey Sports Car Championship",
        "Honday Indy Grand Prix of Alabama",
        "Barber Motorsports Park",
    )

    val regions = listOf(
        "Worldwide",
        "Europe",
        "North America",
        "Asia",
        "Australasia",
        "France",
        "Germany",
        "Great Britain",
        "Japan",
        "Macau",
        "United States of America"
    )

    val categories = listOf(
        "Single Seater",
        "Motorcycle",
        "Sportscar",
        "Touring Car",
        "Stock Car",
        "Rally",
        "Rallycross",
        "One Make",
        "Endurance",
        "GT & Touringcar",
        "Pickup Truck Racing",
        "eSports"
    )

    val leadingSeries = List<UiItem>(10) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
        )
    }

    val categorySeries = List<UiItem>(10) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitles = listOf(categories.random(random)),
        )
    }

    val regionSeries = List<UiItem>(10) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitles = listOf(regions.random(random)),
        )
    }

    val mostRecent = List<UiItem>(10) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = eventNames.random(random),
            subtitles = listOf(
                LocalDate.now().minusDays(it.toLong())
                    .format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            ),
        )
    }

    val series = Series(
        name = names.random(random),
        shortName = names.random(random),
        picture = pictures.random(random),
        firstSeason = random.nextInt(1900..2022).toString(),
        organisation = names.random(random),
        category = categories.random(random),
        links = listOf(
            ResourceLink.YouTube("url"),
            ResourceLink.Facebook("url"),
            ResourceLink.Twitter("url"),
            ResourceLink.Instagram("url"),
        )
    )

    val lastSeriesChampions = LastSeriesChampions(
        drivers = MockDriverData.driverReferences.take(3),
        team = MockTeamData.teamReferences.first(),
    )
}