package com.mss.network.api

import com.mss.network.di.ApiModule
import org.junit.jupiter.api.Test
import utils.testRetrofit

internal class VenueApiTest : AbstractApiTest() {
    private val api = ApiModule.provideVenueApi(testRetrofit)

    @Test
    fun getVenues() = test {
        api.getVenues(
            filterIds = arrayOf("Street Circuit"),
            page = 0,
            size = 10,
        )
    }
}