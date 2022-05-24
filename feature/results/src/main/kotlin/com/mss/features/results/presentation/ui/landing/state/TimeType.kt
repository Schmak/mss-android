package com.mss.features.results.presentation.ui.landing.state

enum class TimeType {
    Client,
    Venue,
    ;

    fun next(): TimeType = when (this) {
        Client -> Venue
        Venue -> Client
    }
}