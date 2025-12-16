package com.sample.fdelamora.samplearch.core.repository.githubclient.extensions

import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUserRepositoriesResponse
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo

fun SearchUserRepositoriesResponse.toGitHubRepoList(): List<GitHubRepo> = items.map { repo ->
    repo.run {
        GitHubRepo(
            id = id,
            name = name,
            fullName = fullName,
            language = language,
            starredCount = starredCount,
            followersCount = watchersCount,
            description = description,
            private = private,
            fork = fork,
            htmlUrl = htmlUrl
        )
    }
}
