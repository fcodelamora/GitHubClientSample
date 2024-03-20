package com.sample.fdelamora.samplearch.core.usecases.feature.githubclient

import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.usecases.IErrorView
import com.sample.fdelamora.samplearch.core.usecases.IProgressView

class GetUserRepositoriesUseCase(
    private val view: IGetUserRepositoriesView,
    private val gitHubRepository: IGitHubRepository
) {
    suspend fun execute(
        user: GitHubUser,
    ) {
        view.showProgressView()

        try {
            val updatedUser = gitHubRepository.userDetails(user.login)
            val foundRepositories = gitHubRepository.searchUserRepositories(user).filter { !it.fork }

            view.onUserRepositoriesReceived(updatedUser, foundRepositories)
        } catch (exception: Exception) {
            view.handleException(exception)
            view.onUserRepositoriesReceived(user, emptyList())
        }

        view.hideProgressView()
    }
}

interface IGetUserRepositoriesView : IErrorView, IProgressView {
    fun onUserRepositoriesReceived(gitHubUser: GitHubUser, repositories: List<GitHubRepo>)
}
