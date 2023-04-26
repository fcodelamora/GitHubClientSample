package com.sample.fdelamora.samplearch.features.githubclient.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.common.resources.ui.SharedErrorsHandler
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.BaseScreenTop
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.LoadingFromNetworkView
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.SearchTextField
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.features.githubclient.R
import com.sample.fdelamora.samplearch.features.githubclient.ui.widgets.GitHubUserCardMinimal
import com.sample.fdelamora.samplearch.features.githubclient.viewmodel.SearchUsersViewModel

object SearchUsersScreen {
    object Default {
        @Composable
        fun Screen(
            viewModel: SearchUsersViewModel,
            onShowUserRepos: (GitHubUser) -> Unit,
        ) {
            SharedErrorsHandler(viewModel = viewModel)
            Content(
                userSearchPrompt = viewModel.userSearchPrompt,
                foundUsers = viewModel.foundUsers,
                onSearchUsersPromptUpdated = viewModel::onSearchUsersPromptUpdated,
                onSearchUsersClick = viewModel::searchUsers,
                onShowUserReposClick = { user -> onShowUserRepos(user) }
            )
            LoadingFromNetworkView(viewModel.isProgressViewVisible.value)
        }

        @Composable
        fun Content(
            userSearchPrompt: String,
            foundUsers: List<GitHubUser>,
            onSearchUsersPromptUpdated: (String) -> Unit = {},
            onSearchUsersClick: () -> Unit = {},
            onShowUserReposClick: (GitHubUser) -> Unit = {},
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                BaseScreenTop(title = stringResource(R.string.search_users_title)) {
                    Box(Modifier.fillMaxWidth()) {
                        SearchTextField(
                            text = userSearchPrompt,
                            placeholder = stringResource(R.string.search_users_textfield_placeholder),
                            onValueChange = onSearchUsersPromptUpdated,
                            onSearchClick = onSearchUsersClick,
                            modifier = Modifier.align(Center)
                        )
                    }
                }
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(count = foundUsers.size, itemContent = { index ->
                        GitHubUserCardMinimal(
                            user = foundUsers[index],
                            onCardTap = { onShowUserReposClick(foundUsers[index]) },
                        )
                    })
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchUsersScreenPreview() = AppUserInterface {
    SearchUsersScreen.Default.Content(
        userSearchPrompt = "User Names",
        foundUsers = listOf(
            GitHubUser(
                login = "login",
                id = 0,
                avatarUrl = "",
                gravatarId = null,
                url = "",
                htmlUrl = "",
                followersUrl = "",
                reposUrl = "",
                publicRepos = null,
                publicGists = null,
                followers = null,
                following = null,
                name = "name"
            )
        )
    )
}
