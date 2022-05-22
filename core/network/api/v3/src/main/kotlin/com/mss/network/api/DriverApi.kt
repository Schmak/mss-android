package com.mss.network.api

import com.mss.network.model.DriverItemDto
import com.mss.network.model.PageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DriverApi {
    @GET("/web/3.0.0/drivers/collections/{collection}")
    suspend fun getCollection(
        @Path("collection") collection: DriverCollection,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): PageDto<DriverItemDto>

    enum class DriverCollection {
        Anniversaries,
        ChampionshipLeaders,
        Debutants,
        RecentWinners,
    }
}