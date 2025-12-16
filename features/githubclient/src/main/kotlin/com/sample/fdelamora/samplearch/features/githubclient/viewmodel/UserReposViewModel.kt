package com.sample.fdelamora.samplearch.features.githubclient.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.fdelamora.samplearch.common.di.UseCaseProvider
import com.sample.fdelamora.samplearch.common.resources.utils.mutableStateOf
import com.sample.fdelamora.samplearch.common.resources.viewmodels.ProgressViewModel
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.IGetUserRepositoriesView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val useCaseProvider: UseCaseProvider
) : ProgressViewModel(application, savedStateHandle),
    IGetUserRepositoriesView {

    var user by savedStateHandle.mutableStateOf<GitHubUser?>(null)
        private set

    var repositories by savedStateHandle.mutableStateOf<List<GitHubRepo>>(emptyList())
        private set

    override fun onUserRepositoriesReceived(
        gitHubUser: GitHubUser,
        repositories: List<GitHubRepo>
    ) {
        this.user = gitHubUser
        this.repositories = repositories
    }

    fun setGitHubUser(user: GitHubUser) {
        this.user = user
        searchRepos()
    }

    fun clearRepos() {
        repositories = emptyList()
    }

    private fun searchRepos() {
        user?.let {
            viewModelScope.launch {
                val useCase = useCaseProvider.provideGetUserRepositoriesUseCase(this@UserReposViewModel)
                useCase.execute(it)
            }
        }
    }
}
