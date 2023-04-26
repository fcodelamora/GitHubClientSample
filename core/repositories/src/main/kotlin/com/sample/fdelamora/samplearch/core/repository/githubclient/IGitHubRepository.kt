package com.sample.fdelamora.samplearch.core.repository.githubclient

import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser

interface IGitHubRepository {
    suspend fun userDetails(
        username: String
    ): GitHubUser

    suspend fun searchUsers(
        keywords: String
    ): List<GitHubUser>

    suspend fun searchUserRepositories(
        user: GitHubUser,
    ): List<GitHubRepo>
}
