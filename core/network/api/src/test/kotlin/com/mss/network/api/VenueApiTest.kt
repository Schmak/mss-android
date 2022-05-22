package com.mss.network.api

import com.mss.core.network.utils.testRetrofit
import com.mss.network.di.ApiModule
import org.junit.jupiter.api.Test

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