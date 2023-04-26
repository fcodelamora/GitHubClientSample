package com.sample.fdelamora.samplearch.provide.mocks.api.di

import com.sample.fdelamora.samplearch.core.api.githubclient.IGitHubApi
import com.sample.fdelamora.samplearch.provide.mocks.api.MockGitHubApi
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.IMockDebugFlagsRepository
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.MockDebugFlags
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.MockDebugFlagsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MockApiModule {

    @Singleton
    @Provides
    fun provideMockDebugFlags() = MockDebugFlags()

    @Singleton
    @Provides
    fun provideGitHubApi(mockDebugFlagsRepository: IMockDebugFlagsRepository): IGitHubApi =
        MockGitHubApi(mockDebugFlagsRepository)
}

@Module
@InstallIn(SingletonComponent::class)
interface MockDebugFlagsRepositoryModule {

    @Binds
    fun bindMockDebugFlagsRepository(
        repository: MockDebugFlagsRepository
    ): IMockDebugFlagsRepository
}
