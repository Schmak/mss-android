package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModule
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