package com.sample.fdelamora.samplearch.core.usecases.feature.githubclient

import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.usecases.IErrorView
import com.sample.fdelamora.samplearch.core.usecases.IProgressView

class SearchUsersUseCase(
    private val view: ISearchUsersUseCaseView,
    private val gitHubRepository: IGitHubRepository
) {
    suspend fun execute(
        prompt: String,
    ) {
        view.showProgressView()

        try {
            val foundUsers = gitHubRepository.searchUsers(prompt)

            view.onUsersFound(foundUsers)
        } catch (exception: Exception) {
            view.handleException(exception)
            view.onUsersFound(emptyList())
        }

        view.hideProgressView()
    }
}

interface ISearchUsersUseCaseView : IErrorView, IProgressView {
    fun onUsersFound(users: List<GitHubUser>)
}
