package com.sample.fdelamora.samplearch.core.usecases.test.githubclient

import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser

internal fun mockGitHubUser(index: Int = 1) = GitHubUser(
    login = "login-$index",
    id = index,
    avatarUrl = "",
    gravatarId = null,
    url = "",
    htmlUrl = "",
    followersUrl = "",
    reposUrl = "",
    publicRepos = index,
    publicGists = index,
    followers = index,
    following = index,
    name = "name-$$index"
)

internal fun mockGitHubRepo(index: Int = 1) = GitHubRepo(
    id = index,
    name = "name-$index",
    fullName = "fullname-$index",
    language = null,
    starredCount = index,
    followersCount = index,
    description = null,
    private = false,
    fork = false,
    htmlUrl = ""
)
