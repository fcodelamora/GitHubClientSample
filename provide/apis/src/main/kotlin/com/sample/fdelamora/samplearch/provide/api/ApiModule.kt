package com.sample.fdelamora.samplearch.provide.api

import com.sample.fdelamora.samplearch.core.api.githubclient.IGitHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideGitHubApi(): IGitHubApi = IGitHubApi.provide(
        baseUrl = ApisBuildConfig.API_BASE_URL_GITHUB,
        apiKey = ApisBuildConfig.API_KEY_GITHUB,
        isOutputEnabled = ApisBuildConfig.DEBUG
    )
}
