package com.mss.network.api

import com.mss.network.api.DriverApi.DriverCollection
import com.mss.network.di.ApiModule
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import utils.testRetrofit

internal class DriverApiTest : AbstractApiTest() {
    private val api = ApiModule.provideDriverApi(testRetrofit)

    @ParameterizedTest
    @EnumSource(DriverCollection::class)
    fun getCollection(collection: DriverCollection) = test {
        api.getCollection(
            collection = collection,
            page = 0,
            size = 10,
        )
    }
}