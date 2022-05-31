package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.api.TeamApiV4.TeamCollection
import com.mss.core.network.v4.di.ApiV4Module
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class TeamApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideTeamApi(testRetrofit)

    @ParameterizedTest
    @EnumSource(TeamCollection::class)
    fun getCollection(collection: TeamCollection) = assertIsNotEmpty {
        api.getCollection(
            collection = collection,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getInfo() = assertIsNotEmpty {
        api.getTeamInfo(TEAM)
    }

    @Test
    fun getMissingInfo() = assertThrows404 {
        api.getTeamInfo(MISSING_TEAM)
    }

    @Test
    fun getLastDrivers() = assertIsNotEmpty {
        api.getLastDrivers(TEAM)
    }

    @Test
    fun getMissingLastDrivers() = assertIsEmpty {
        api.getLastDrivers(MISSING_TEAM)
    }

    companion object {
        private const val TEAM = "ferrari"
        private const val MISSING_TEAM = "fer"
    }
}