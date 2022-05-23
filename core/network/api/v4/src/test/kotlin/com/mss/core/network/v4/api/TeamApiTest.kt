package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.api.TeamApiV4.TeamCollection
import com.mss.core.network.v4.di.ApiV4Module
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class TeamApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideTeamApi(testRetrofit)

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