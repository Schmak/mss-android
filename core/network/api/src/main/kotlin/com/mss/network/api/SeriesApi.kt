package com.mss.network.api

import com.mss.network.model.PageDto
import com.mss.network.model.SeriesInfoDto
import com.mss.network.model.SeriesItemDto
import com.mss.network.model.TeamItemDto
import com.mss.network.model.sort.OrderByDto
import com.mss.network.model.sort.SortFieldDto
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

    @GET("/web/4.0.0/series/{series}/teams")
    suspend fun getTeams(
        @Path("series") series: String,
        @Query("hideZeros") hideZeros: Boolean,
        @Query("orderBy") orderBy: OrderByDto<TeamOrder>,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<TeamItemDto>

    enum class SeriesOrder {
        LastEventDate,
    }

    enum class TeamOrder : SortFieldDto {
        ChampionshipWins,
        TeamWins,
    }
}