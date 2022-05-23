package com.mss.core.network.v3.api

import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.VenueItemDto
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