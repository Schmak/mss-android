package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.di.ApiV4Module
import org.junit.jupiter.api.Test

internal class DriverApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideDriverApi(testRetrofit)

    @Test
    fun getLastTeams() = assertIsNotEmpty {
        api.getLastTeams(DRIVER)
    }

    @Test
    fun getMissingLastTeams() = assertIsEmpty {
        api.getLastTeams(MISSING_DRIVER)
    }

    companion object {
        private const val DRIVER = "lewis-hamilton"
        private const val MISSING_DRIVER = "hamilton"
    }
}