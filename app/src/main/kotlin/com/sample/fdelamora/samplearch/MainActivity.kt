package com.sample.fdelamora.samplearch

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.sample.fdelamora.samplearch.common.resources.ui.AppUserInterface
import com.sample.fdelamora.samplearch.common.resources.ui.SampleArchitectureScreens
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.Animations
import com.sample.fdelamora.samplearch.features.githubclient.ui.screens.SearchUsersScreen
import com.sample.fdelamora.samplearch.features.githubclient.ui.screens.UserReposScreen
import com.sample.fdelamora.samplearch.features.githubclient.viewmodel.SearchUsersViewModel
import com.sample.fdelamora.samplearch.features.githubclient.viewmodel.UserReposViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchUsersViewModel: SearchUsersViewModel by viewModels()
    private val userReposViewModel: UserReposViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(SystemBarStyle.dark(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)

        // Go Fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SampleArchitectureApp(
                startScreenRoute = SampleArchitectureScreens.GitHubClient.SearchUsers.name,
            )
        }
    }

    @Composable
    fun SampleArchitectureApp(startScreenRoute: String) {
        AppUserInterface {
            val navController = rememberNavController()

            Box {
                SampleArchitectureNavHost(
                    navController = navController,
                    startScreenRoute = startScreenRoute,
                )
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    fun SampleArchitectureNavHost(
        navController: NavHostController,
        startScreenRoute: String,
        modifier: Modifier = Modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = startScreenRoute,
            modifier = modifier,
        ) {
            composable(
                route = SampleArchitectureScreens.GitHubClient.SearchUsers.name,
                enterTransition = Animations.enterTransition,
                exitTransition = Animations.exitTransition,
                popEnterTransition = Animations.popEnterTransition,
                popExitTransition = Animations.popExitTransition,
            ) {
                SearchUsersScreen.Default.Screen(
                    viewModel = searchUsersViewModel,
                    onShowUserRepos = { user ->
                        userReposViewModel.clearRepos()
                        userReposViewModel.setGitHubUser(user)
                        navController.navigate(SampleArchitectureScreens.GitHubClient.UserRepos.name)
                    },
                )
            }
            composable(
                route = SampleArchitectureScreens.GitHubClient.UserRepos.name,
                enterTransition = Animations.enterTransition,
                exitTransition = Animations.exitTransition,
                popEnterTransition = Animations.popEnterTransition,
                popExitTransition = Animations.popExitTransition,
            ) {
                UserReposScreen.Default.Screen(
                    viewModel = userReposViewModel,
                    navController = navController,
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview() {
    AppUserInterface {
        SearchUsersScreen.Default.Content(
            userSearchPrompt = "",
            foundUsers = listOf(),
            onSearchUsersPromptUpdated = {},
            onSearchUsersClick = {},
            onShowUserReposClick = {},
        )
    }
}
