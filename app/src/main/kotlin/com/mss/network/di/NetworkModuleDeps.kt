package com.mss.network.di

import com.mss.app.BuildConfig
import com.mss.network.ApiServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleDeps {
    @Provides
    fun provideApiServer(): ApiServer = object : ApiServer {
        override val url: String
            get() = BuildConfig.SERVER_URL

        override val apiKey: String
            get() = BuildConfig.API_KEY
    }
}