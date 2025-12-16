package com.sample.fdelamora.samplearch.common.resources.ui.catalogs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

// Ref: https://www.youtube.com/watch?v=6JRYm39bpaA
@OptIn(ExperimentalAnimationApi::class)
object Animations {
    const val DefaultScreenTransitionDuration = 300
    private const val DefaultEnterOffset = 300

    // MEMO:  MUST generate a new Transition instance each time.
    // If the same instance is created and shared it breaks the animated navhost behavior

    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
        get() = {
            slideInHorizontally(
                initialOffsetX = { DefaultEnterOffset },
                animationSpec =
                tween(
                    durationMillis = DefaultScreenTransitionDuration,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(durationMillis = DefaultScreenTransitionDuration))
        }

    val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)
        get() = {
            slideOutHorizontally(
                targetOffsetX = { -DefaultEnterOffset },
                animationSpec = tween(DefaultScreenTransitionDuration)
            ) + fadeOut(animationSpec = tween(durationMillis = DefaultScreenTransitionDuration))
        }

    val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
        get() = {
            slideInHorizontally(
                initialOffsetX = { -DefaultEnterOffset },
                animationSpec = tween(DefaultScreenTransitionDuration)
            ) + fadeIn(animationSpec = tween(durationMillis = DefaultScreenTransitionDuration))
        }

    val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)
        get() = {
            slideOutHorizontally(
                targetOffsetX = { DefaultEnterOffset },
                animationSpec = tween(DefaultScreenTransitionDuration)
            ) + fadeOut(animationSpec = tween(durationMillis = DefaultScreenTransitionDuration))
        }
}
