package com.sample.fdelamora.samplearch.common.di

import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.GetUserRepositoriesUseCase
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.IGetUserRepositoriesView
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.ISearchUsersUseCaseView
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.SearchUsersUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCaseProvider @Inject constructor(
    private val gitHubRepository: IGitHubRepository
) {
    fun provideSearchUsersUseCase(
        view: ISearchUsersUseCaseView,
    ) = SearchUsersUseCase(
        view,
        gitHubRepository
    )

    fun provideGetUserRepositoriesUseCase(
        view: IGetUserRepositoriesView,
    ) = GetUserRepositoriesUseCase(
        view,
        gitHubRepository
    )
}
