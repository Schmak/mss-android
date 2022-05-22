package com.mss.core.domain.repository

import com.mss.core.domain.VenueItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.utils.Result

interface VenueRepository {
    suspend fun getSeasonVenues(
        season: String,
        pageable: Pageable
    ): Result<Page<VenueItem>>

    suspend fun getCollection(
        collection: Collection,
        pageable: Pageable
    ): Result<Page<VenueItem>>

    enum class Collection(val title: String) {
        RaceCircuit("Race Circuit"),
        Rallycross("Rallycross"),
        RoadCircuit("Road Circuit"),
        StreetCircuit("Street Circuit"),
        ;
    }
}