package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.VenueDetailsDto
import com.mss.core.network.v4.model.VenueDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VenueApiV4 {
    @GET("/web/4.0.0/venues/{venue}/short")
    suspend fun getInfo(
        @Path("venue") venue: String
    ): VenueDto

    @GET("/web/4.0.0/venues/{venue}/details")
    suspend fun getDetails(
        @Path("venue") venue: String
    ): VenueDetailsDto
}