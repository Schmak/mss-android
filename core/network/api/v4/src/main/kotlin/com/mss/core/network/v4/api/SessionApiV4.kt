package com.mss.core.network.v4.api

import com.mss.core.network.v4.model.PageDto
import com.mss.core.network.v4.model.SessionItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SessionApiV4 {
    @GET("/web/4.0.0/sessions/collections/{collection}")
    suspend fun getCollection(
        @Path("collection") collection: SessionCollection,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("status") status: SeriesStatus?,
    ): PageDto<SessionItemDto>

    @GET("/web/4.0.0/sessions/collections/seriesCategory/{category}")
    suspend fun getSeriesCategorySessions(
        @Path("category") category: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("status") status: SeriesStatus?,
    ): PageDto<SessionItemDto>

    enum class SessionCollection {
        MostRecent,
        MostPopular,
        Forthcoming,
    }

    enum class SeriesStatus {
        Active,
        Defunct,
        Unknown,
    }
}