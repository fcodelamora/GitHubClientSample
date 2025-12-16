package com.sample.fdelamora.samplearch.core.repository.githubclient.extensions

import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUsersResponse
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser

fun SearchUsersResponse.toGitHubUserList(): List<GitHubUser> = items.map { user ->
    user.run {
        GitHubUser(
            login = login,
            id = id,
            avatarUrl = avatarUrl,
            gravatarId = gravatarId,
            url = url,
            htmlUrl = htmlUrl,
            followersUrl = followersUrl,
            reposUrl = reposUrl,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            name = name
        )
    }
}
