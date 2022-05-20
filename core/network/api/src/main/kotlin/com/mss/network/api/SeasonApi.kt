package com.mss.network.api

import com.mss.network.model.PageDto
import com.mss.network.model.TeamItemDto
import com.mss.network.model.sort.OrderByDto
import com.mss.network.model.sort.SortFieldDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeasonApi {
    @GET("/web/4.0.0/seasons/{season}/teams")
    suspend fun getTeams(
        @Path("season") season: String,
        @Query("hideZeros") hideZeros: Boolean,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("orderBy") orderBy: OrderByDto<TeamOrder>,
    ): PageDto<TeamItemDto>

    enum class TeamOrder : SortFieldDto {
        ChampionshipPosition,
    }
}