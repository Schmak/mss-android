package com.mss.core.network.di

import com.mss.network.ApiServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpClient(apiServer: ApiServer): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request()
                    .newBuilder()
                    .addHeader("x-api-key", apiServer.apiKey)
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        apiServer: ApiServer,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiServer.url)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
}