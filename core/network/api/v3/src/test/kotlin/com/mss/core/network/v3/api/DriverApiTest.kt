package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.api.DriverApiV3.DriverCollection
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class DriverApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideDriverApi(testRetrofit)

    @ParameterizedTest
    @EnumSource(DriverCollection::class)
    fun getCollection(collection: DriverCollection) = assertIsNotEmpty {
        api.getCollection(
            collection = collection,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getDriverInfo() = assertIsNotEmpty {
        api.getDriverInfo(DRIVER)
    }

    @Test
    fun getMissingDriverInfo() = assertThrows404 {
        api.getDriverInfo(MISSING_DRIVER)
    }

    companion object {
        private const val DRIVER = "lewis-hamilton"
        private const val MISSING_DRIVER = "hamilton"
    }
}