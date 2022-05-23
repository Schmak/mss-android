package com.mss.network.api

import com.mss.network.model.DriverItemDto
import com.mss.network.model.PageDto
import com.mss.network.model.VenueItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeasonApi {
    @GET("/web/3.0.0/seasons/{season}/drivers")
    suspend fun getDrivers(
        @Path("season") season: String,
        @Query("hideZeros") hideZeros: Boolean,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("orderBy") orderBy: DriverOrder,
        @Query("orderDescending") orderDescending: Boolean,
    ): PageDto<DriverItemDto>

    @GET("/web/3.0.0/seasons/{season}/venues")
    suspend fun getVenues(
        @Path("season") season: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<VenueItemDto>

    enum class DriverOrder {
        ChampionshipPosition,
    }
}