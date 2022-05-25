package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.LastSeriesChampionsDto
import com.mss.core.network.v4.model.PageDto
import com.mss.core.network.v4.model.SeriesDto
import com.mss.core.network.v4.model.TeamItemDto
import com.mss.core.network.v4.model.sort.OrderByDto
import com.mss.core.network.v4.model.sort.SortFieldDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiV4 {
    @GET("/web/4.0.0/series/{series}/teams")
    suspend fun getTeams(
        @Path("series") series: String,
        @Query("hideZeros") hideZeros: Boolean,
        @Query("orderBy") orderBy: OrderByDto<TeamOrder>,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<TeamItemDto>

    @GET("/web/4.0.0/series/{series}/short")
    suspend fun getInfo(
        @Path("series") series: String
    ): SeriesDto

    @GET("/web/4.0.0/series/{series}/lastChampions")
    suspend fun getLastChampions(
        @Path("series") series: String
    ): LastSeriesChampionsDto

    enum class TeamOrder : SortFieldDto {
        ChampionshipWins,
        TeamWins,
    }
}