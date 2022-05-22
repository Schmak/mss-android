package com.mss.network.api

import com.mss.core.network.utils.testRetrofit
import com.mss.network.api.TeamApi.TeamCollection
import com.mss.network.di.ApiModule
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class TeamApiTest : AbstractApiTest() {
    private val api = ApiModule.provideTeamApi(testRetrofit)

    @ParameterizedTest
    @EnumSource(TeamCollection::class)
    fun getCollection(collection: TeamCollection) = test {
        api.getCollection(
            collection = collection,
            page = 0,
            size = 10,
        )
    }
}