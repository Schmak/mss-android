package com.mss.network.api

import com.mss.network.model.PageDto
import com.mss.network.model.VenueItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueApi {
    @GET("/web/3.0.0/venues")
    suspend fun getVenues(
        @Query("filterIds") filterIds: Array<String>,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<VenueItemDto>
}