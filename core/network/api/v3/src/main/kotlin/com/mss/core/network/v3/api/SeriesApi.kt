package com.mss.core.network.v3.api

import com.mss.core.network.v3.model.DriverItemDto
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.SeriesInfoDto
import com.mss.core.network.v3.model.SeriesItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {
    @GET("/web/3.0.0/series/categories")
    suspend fun getCategories(): List<String>

    @GET("/web/3.0.0/series/regions")
    suspend fun getRegions(): List<String>

    @GET("/web/3.0.0/series")
    suspend fun getSeries(
        @Query("filterIds") filterIds: Array<String>,
        @Query("orderBy") orderBy: SeriesOrder?,
        @Query("orderDescending") orderDescending: Boolean?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<SeriesItemDto>

    @GET("/web/3.0.0/series/golden")
    suspend fun getGoldenSeries(): List<SeriesInfoDto>

    @GET("/web/3.0.0/series/collection")
    suspend fun getCollection(
        @Query("region") region: String?,
        @Query("category") category: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<SeriesItemDto>

    @GET("/web/3.0.0/series/{series}/drivers")
    suspend fun getDrivers(
        @Path("series") series: String,
        @Query("hideZeros") hideZeros: Boolean,
        @Query("orderBy") orderBy: DriverOrder,
        @Query("orderDescending") orderDescending: Boolean,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<DriverItemDto>

    enum class SeriesOrder {
        LastEventDate,
    }

    enum class DriverOrder {
        ChampionshipWins,
        Wins,
    }
}