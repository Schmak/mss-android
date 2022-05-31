package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.SeriesWithTeamDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DriverApiV4 {
    @GET("/web/4.0.0/drivers/{driver}/series/withLastTeams")
    suspend fun getLastTeams(
        @Path("driver") driver: String,
    ): List<SeriesWithTeamDto>
}