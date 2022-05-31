package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.api.Test

internal class VenueApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideVenueApi(testRetrofit)

    @Test
    fun getVenues() = assertIsNotEmpty {
        api.getVenues(
            filterIds = arrayOf("Street Circuit"),
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getMissingVenues() = assertIsEmpty {
        api.getVenues(
            filterIds = arrayOf("Hamilton Lewis"),
            page = 0,
            size = 10,
        )
    }
}