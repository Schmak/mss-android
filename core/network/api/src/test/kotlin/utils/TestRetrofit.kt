package utils

import com.mss.network.di.NetworkModule

val testRetrofit = NetworkModule.provideRetrofit(
    client = NetworkModule.provideHttpClient(apiServer = TestApiServer),
    gsonConverterFactory = NetworkModule.providesGsonConverterFactory(),
    apiServer = TestApiServer
)