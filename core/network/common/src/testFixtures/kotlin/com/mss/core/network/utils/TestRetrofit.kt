package com.mss.core.network.utils

import com.mss.core.network.di.NetworkModule

val testRetrofit = NetworkModule.provideRetrofit(
    client = NetworkModule.provideHttpClient(apiServer = TestApiServer),
    gsonConverterFactory = NetworkModule.providesGsonConverterFactory(),
    apiServer = TestApiServer
)