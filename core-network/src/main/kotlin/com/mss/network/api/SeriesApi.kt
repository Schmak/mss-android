package com.mss.network.api

import com.mss.network.model.PageDto
import com.mss.network.model.SeriesInfoDto
import com.mss.network.model.SeriesItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesApi {
    @GET("/web/3.0.0/series/categories")
    suspend fun getCategories(): List<String>

    @GET("/web/3.0.0/series/regions")
    suspend fun getRegions(): List<String>

    @GET("/web/3.0.0/series")
    suspend fun getSeries(
        @Query("filterIds") filterIds: Array<String>,
        @Query("orderBy") orderBy: OrderBy?,
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

    enum class OrderBy {
        LastEventDate,
    }
}