package com.mss.core.ui.navigation

sealed class Route(
    val value: String,
    val arguments: List<String> = emptyList(),
) {
    object Series : Route("series")
    object Results : Route("results")
    object Drivers : Route("drivers")
    object Teams : Route("teams")
    object Venues : Route("venues")

    class SeriesInfo(seriesSlug: String) : Route(
        value = "series/$seriesSlug",
        arguments = listOf(Arguments.SERIES_SLUG),
    ) {
        object Arguments {
            const val SERIES_SLUG = "seriesSlug"
        }

        companion object {
            val INSTANCE = SeriesInfo(seriesSlug = "{${Arguments.SERIES_SLUG}}")
        }
    }
}