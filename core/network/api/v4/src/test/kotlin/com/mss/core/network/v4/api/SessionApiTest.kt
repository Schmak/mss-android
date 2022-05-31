package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.api.SessionApiV4.SeriesStatus.Active
import com.mss.core.network.v4.api.SessionApiV4.SessionCollection
import com.mss.core.network.v4.di.ApiV4Module
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class SessionApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideSessionApi(testRetrofit)

    @ParameterizedTest
    @EnumSource(SessionCollection::class, mode = EnumSource.Mode.EXCLUDE, names = ["Forthcoming"])
    fun getCollection(collection: SessionCollection) = assertIsNotEmpty {
        api.getCollection(
            collection = collection,
            page = 0,
            size = 10,
            status = Active,
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["Single Seater", "Rally", "Rallycross"])
    fun getSeriesCategorySessions(category: String) = assertIsNotEmpty {
        api.getSeriesCategorySessions(
            category = category,
            page = 0,
            size = 10,
            status = Active,
        )
    }
}