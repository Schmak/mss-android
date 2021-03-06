package com.mss.core.ui.data.mock

import com.mss.core.domain.ref.TeamReference
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import kotlin.random.Random

@Suppress("MagicNumber")
object MockTeamData {
    private val random = Random(12_345)

    val pictures = listOf(
        "4aeca1ca-721b-4c7a-82fd-65d4c75c88ce", "17a165a9-3184-4a7b-9a38-3301b022537a",
        "12ed024d-f43c-4777-b2af-9c0dd0e6481b", "dca36bc9-3d1d-4a61-a977-03bd38492cb9",
        "d88db12f-2b71-4d14-b7f6-5ab4bde459b8", "e8179b9a-212f-453a-a820-73fa3637503d",
        "076fa4bd-3d8c-4888-96b7-acaa93296ca0", "b3903f0c-f0f1-44d6-920a-d06c285816af",
        "48a87d96-733a-478a-a804-67df763071e3", "65acb6ae-460b-42cd-8e9b-43f659d39256",
        "error"
    ).map { "https://content.motorsportstats.com/teamProfilePicture/teamProfilePicture-$it.jpg" }

    val names = listOf(
        "Gresini Racing MotoGP", "Mercedes-EQ Formula E Team", "Prema Racing", "Team WRT",
        "23XI Racing", "Red Bull KTM Ajo", "AKKODIS ASP Team", "Carlin", "Full Motorsport",
        "Hitech Grand Prix", "Dynavolt Intact GP MotoE", "Konica Minolta Acura",
        "Pons Racing 40", "Rich Energy BTC Racing", "Team BMW",
    )

    val currentSeasonTeams = getTeamList()
    val champions = getTeamList()
    val winners = getTeamList()
    val collectionTeams = getTeamList()
    val teamReferences = List(10) {
        TeamReference(
            name = names.random(random),
            slug = "",
            picture = pictures.random(random),
        )
    }

    private fun getTeamList(size: Int = 10) = List<UiItem>(size) {
        SimpleUiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitles = listOf(MockCountryData.names.random()),
        )
    }
}