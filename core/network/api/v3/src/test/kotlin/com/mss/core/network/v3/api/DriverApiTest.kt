package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.api.DriverApi.DriverCollection
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class DriverApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideDriverApi(testRetrofit)

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