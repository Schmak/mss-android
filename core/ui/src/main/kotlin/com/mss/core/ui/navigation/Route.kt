package com.mss.core.ui.navigation

sealed class Route(val value: String) {
    object Series : Route("series")
    object Results : Route("results")
    object Drivers : Route("drivers")
    object Teams : Route("teams")
    object Venues : Route("venues")
}