package com.mss.core.ui.data.mock

import com.mss.core.ui.data.mock.MockCountryData.countries
import com.mss.core.ui.model.UiItem
import kotlin.random.Random

@Suppress("MagicNumber")
object MockVenueData {
    private val random = Random(12_345)

    private val pictures = listOf(
        "6603e884-bb0e-4423-aff0-5aa95c6a8e96", "4c7524d4-70ec-4bc7-b674-5b9dc8197d46",
        "1d7c83be-7939-437a-b0b2-be4833da18d1", "cc0117d2-7b7a-46f9-9714-7a336cdf960e",
        "b044972f-ad1f-4551-ba69-727c24f5158e", "2ec7adab-816d-4b4b-b700-5000bd81f692",
        "c1dcd55b-5881-459b-902d-76050107b695", "57e9d548-9f75-4987-91d7-d4aad46d9184",
        "06940aee-5204-43ee-8a60-d8cc4c1c6d44", "a65370c3-6b5c-4f13-b5f8-aa9ae1899068",
        "error"
    ).map { "https://content.motorsportstats.com/venueProfilePicture/venueProfilePicture-$it.jpg" }

    private val names = listOf(
        "Circuit de Barcelona-Catalunya", "Miami International Autodrome",
        "Autodromo Enzo e Dino Ferrari", "Melbourne Grand Prix Circuit",
        "Jeddah Corniche Circuit", "Bahrain International Circuit",
        "Circuit de Monaco", "Baku City Circuit", "Circuit Gilles Villeneuve",
        "Silverstone Circuit", "Red Bull Ring", "Circuit Paul Ricard ", "Hungaroring",
        "Circuit de Spa-Francorchamps", "Circuit Zandvoort",
    )

    val currentSeasonVenues = getVenueList()
    val raceCircuitVenues = getVenueList()
    val rallycrossVenues = getVenueList()
    val roadCircuitVenues = getVenueList()
    val streetCircuitVenues = getVenueList()

    private fun getVenueList(size: Int = 10) = List(size) {
        UiItem(
            imageUrl = pictures.random(random),
            title = names.random(random),
            subtitles = listOf(countries.random()),
        )
    }
}