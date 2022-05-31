package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.di.ApiV4Module
import org.junit.jupiter.api.Test

internal class VenueApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideVenueApi(testRetrofit)

    @Test
    fun getInfo() = assertIsNotEmpty {
        api.getInfo(VENUE)
    }

    @Test
    fun getMissingVenueInfo() = assertThrows404 {
        api.getInfo(WRONG_VENUE)
    }

    @Test
    fun getDetails() = assertIsNotEmpty {
        api.getDetails(VENUE)
    }

    @Test
    fun getMissingVenueDetails() = assertThrows404 {
        api.getDetails(WRONG_VENUE)
    }

    companion object {
        private const val VENUE = "circuit-de-monaco"
        private const val WRONG_VENUE = "monaco"
    }
}