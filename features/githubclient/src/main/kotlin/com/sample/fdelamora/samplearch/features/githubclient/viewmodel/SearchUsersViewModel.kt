package com.sample.fdelamora.samplearch.features.githubclient.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.fdelamora.samplearch.common.di.UseCaseProvider
import com.sample.fdelamora.samplearch.common.resources.utils.mutableStateOf
import com.sample.fdelamora.samplearch.common.resources.viewmodels.ProgressViewModel
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.ISearchUsersUseCaseView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUsersViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val useCaseProvider: UseCaseProvider
) : ProgressViewModel(application, savedStateHandle),
    ISearchUsersUseCaseView {

    var userSearchPrompt by savedStateHandle.mutableStateOf<String>("")
        private set

    var foundUsers by savedStateHandle.mutableStateOf<List<GitHubUser>>(emptyList())
        private set

    override fun onUsersFound(users: List<GitHubUser>) {
        foundUsers = users
    }

    fun searchUsers() {
        if (!userSearchPrompt.isNullOrBlank()) {

            viewModelScope.launch {
                val useCase = useCaseProvider.provideSearchUsersUseCase(this@SearchUsersViewModel)
                useCase.execute(userSearchPrompt)
            }
        }
    }

    fun onSearchUsersPromptUpdated(prompt: String) {
        if (prompt.contains("\n")) {
            searchUsers()
        } else {
            userSearchPrompt = prompt
        }
    }
}
