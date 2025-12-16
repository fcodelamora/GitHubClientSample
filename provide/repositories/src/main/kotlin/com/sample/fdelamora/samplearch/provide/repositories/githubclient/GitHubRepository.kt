package com.sample.fdelamora.samplearch.provide.repositories.githubclient

import com.sample.fdelamora.samplearch.core.api.error.ApiException
import com.sample.fdelamora.samplearch.core.api.githubclient.IGitHubApi
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUserRepositoriesRequestParams
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUsersRequestParams
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubRepo
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.repository.extensions.toApplicationException
import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.repository.githubclient.extensions.toGitHubRepoList
import com.sample.fdelamora.samplearch.core.repository.githubclient.extensions.toGitHubUser
import com.sample.fdelamora.samplearch.core.repository.githubclient.extensions.toGitHubUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val gitHubApi: IGitHubApi) :
    IGitHubRepository {

    override suspend fun userDetails(username: String): GitHubUser = withContext(Dispatchers.IO) {
        try {
            gitHubApi.userDetails(username)
                .toGitHubUser()
        } catch (exception: ApiException) {
            throw exception.toApplicationException()
        }
    }

    override suspend fun searchUsers(keywords: String): List<GitHubUser> = withContext(Dispatchers.IO) {
        try {
            gitHubApi.searchUsers(
                GetSearchUsersRequestParams(keywords = keywords)
            ).toGitHubUserList()
        } catch (exception: ApiException) {
            throw exception.toApplicationException()
        }
    }

    override suspend fun searchUserRepositories(user: GitHubUser): List<GitHubRepo> = withContext(Dispatchers.IO) {
        try {
            gitHubApi.searchUserRepositories(
                GetSearchUserRepositoriesRequestParams(login = user.login)
            ).toGitHubRepoList()
        } catch (exception: ApiException) {
            throw exception.toApplicationException()
        }
    }
}
