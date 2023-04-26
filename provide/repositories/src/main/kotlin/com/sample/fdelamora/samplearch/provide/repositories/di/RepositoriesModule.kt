package com.sample.fdelamora.samplearch.provide.repositories.di

import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.provide.repositories.githubclient.GitHubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindGitHubRepository(
        repository: GitHubRepository
    ): IGitHubRepository
}
