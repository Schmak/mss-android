package com.mss.network.api

import com.mss.network.model.PageDto
import com.mss.network.model.TeamItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamApi {
    @GET("/web/4.0.0/teams/collections/{collection}")
    suspend fun getCollection(
        @Path("collection") collection: TeamCollection,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<TeamItemDto>

    enum class TeamCollection {
        RecentWinners,
        ChampionshipLeaders,
    }
}