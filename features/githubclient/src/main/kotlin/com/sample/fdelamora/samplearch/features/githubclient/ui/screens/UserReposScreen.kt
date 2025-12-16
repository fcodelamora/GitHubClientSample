package com.sample.fdelamora.samplearch.features.githubclient.ui.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.common.resources.ui.SharedErrorsHandler
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.BaseScreenTop
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views.LoadingFromNetworkView
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.features.githubclient.R
import com.sample.fdelamora.samplearch.features.githubclient.ui.widgets.GitHubRepoCard
import com.sample.fdelamora.samplearch.features.githubclient.ui.widgets.GitHubUserDetails
import com.sample.fdelamora.samplearch.features.githubclient.viewmodel.UserReposViewModel

object UserReposScreen {
    object Default {
        @Composable
        fun Screen(
            viewModel: UserReposViewModel,
            navController: NavController
        ) {
            val context = LocalContext.current

            SharedErrorsHandler(viewModel = viewModel)
            Content(
                user = viewModel.user,
                repositories = viewModel.repositories,
                onShowRepoDetails = { repo -> openCustomTab(repo.htmlUrl, context) },
                onNavigateBack = { navController.navigateUp() }
            )
            LoadingFromNetworkView(viewModel.isProgressViewVisible.value)
        }

        @Composable
        fun Content(
            user: GitHubUser?,
            repositories: List<GitHubRepo> = emptyList(),
            onShowRepoDetails: (GitHubRepo) -> Unit = {},
            onNavigateBack: () -> Unit = {}
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                BaseScreenTop(
                    title = stringResource(R.string.user_repos_no_forks_title),
                    onNavigateBack = onNavigateBack
                ) {
                    user?.let {
                        GitHubUserDetails(gitHubUser = it)
                    }
                }
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(count = repositories.size, itemContent = { index ->
                        GitHubRepoCard(
                            repo = repositories[index],
                            onCardTap = { onShowRepoDetails(repositories[index]) }
                        )
                    })
                }
            }
        }
    }
}

private fun openCustomTab(
    url: String,
    context: Context
) {
    val packageName = "com.android.chrome"
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    builder.setInstantAppsEnabled(true)

    val customBuilder = builder.build()
    customBuilder.intent.setPackage(packageName)
    customBuilder.launchUrl(context, Uri.parse(url))
}

@Preview
@Composable
private fun UserReposScreenPreview() = AppUserInterface {
    UserReposScreen.Default.Content(
        user = GitHubUser(
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
        ),
        repositories = listOf(
            GitHubRepo(
                id = 0,
                name = "Name",
                fullName = "Fullname",
                language = "Language",
                starredCount = 1,
                followersCount = 2,
                description = "Description",
                private = false,
                fork = false,
                htmlUrl = "https://www.google.com"
            )
        ),
        onShowRepoDetails = {},
        onNavigateBack = {}
    )
}
