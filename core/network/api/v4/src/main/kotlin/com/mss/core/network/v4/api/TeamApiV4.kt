package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.PageDto
import com.mss.core.network.v4.model.SeriesWithDriversDto
import com.mss.core.network.v4.model.TeamDto
import com.mss.core.network.v4.model.TeamItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamApiV4 {
    @GET("/web/4.0.0/teams/collections/{collection}")
    suspend fun getCollection(
        @Path("collection") collection: TeamCollection,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<TeamItemDto>

    @GET("/web/4.0.0/teams/{team}/short")
    suspend fun getTeamInfo(
        @Path("team") team: String
    ): TeamDto

    @GET("/web/4.0.0/teams/{team}/series/withLastDrivers")
    suspend fun getLastDrivers(
        @Path("team") team: String,
    ): List<SeriesWithDriversDto>

    enum class TeamCollection {
        RecentWinners,
        ChampionshipLeaders,
    }
}