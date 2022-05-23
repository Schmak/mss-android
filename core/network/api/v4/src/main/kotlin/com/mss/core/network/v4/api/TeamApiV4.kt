package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.PageDto
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

    enum class TeamCollection {
        RecentWinners,
        ChampionshipLeaders,
    }
}