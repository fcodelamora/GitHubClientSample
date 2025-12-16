package com.sample.fdelamora.samplearch.provide.mocks.api

import com.sample.fdelamora.samplearch.core.api.githubclient.IGitHubApi
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUserRepositoriesRequestParams
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUsersRequestParams
import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUserRepositoriesDetails
import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUserRepositoriesResponse
import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUsersResponse
import com.sample.fdelamora.samplearch.core.api.githubclient.response.UserDetailsResponse
import com.sample.fdelamora.samplearch.provide.mocks.api.debugflags.IMockDebugFlagsRepository
import co.touchlab.kermit.Logger

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockGitHubApi @Inject constructor(private val mockDebugFlagsRepository: IMockDebugFlagsRepository) :
    IGitHubApi {

    override suspend fun userDetails(username: String): UserDetailsResponse {
        mockDebugFlagsRepository.handleError()
        mockDebugFlagsRepository.loadMockDebugFlags().run {
            val mock = generateSearchUsersResponse(
                usersToGenerate = userCount,
                isIncompleteResponse = isIncompleteResponse
            ).items.first()

            return mock.copy(
                // Simulate the result from the API where below data is returned.
                name = "Name $username",
                login = username,
                followers = 10,
                following = 100,
                avatarUrl = "https://via.placeholder.com/250x250.png/AEB1BF/00146E?text=$username"
            ).also {
                Logger.d { it.toString() }
            }
        }
    }

    override suspend fun searchUsers(requestParams: GetSearchUsersRequestParams): SearchUsersResponse {
        mockDebugFlagsRepository.handleError()
        mockDebugFlagsRepository.loadMockDebugFlags().run {
            return generateSearchUsersResponse(
                usersToGenerate = userCount,
                isIncompleteResponse = isIncompleteResponse
            ).also {
                Logger.d { it.toString() }
            }
        }
    }

    override suspend fun searchUserRepositories(requestParams: GetSearchUserRepositoriesRequestParams):
        SearchUserRepositoriesResponse {
        mockDebugFlagsRepository.handleError()
        mockDebugFlagsRepository.loadMockDebugFlags().run {
            return generateSearchUserRepositoriesResponse(
                reposToGenerate = repoCount,
                isIncompleteResponse = isIncompleteResponse
            ).also {
                Logger.d { it.toString() }
            }
        }
    }

    private fun generateSearchUsersResponse(
        usersToGenerate: Int,
        isIncompleteResponse: Boolean
    ): SearchUsersResponse {
        val userList = mutableListOf<UserDetailsResponse>()

        (1..usersToGenerate).forEach { index ->
            userList.add(getMockGitHubUser(index))
        }
        return SearchUsersResponse(
            totalCount = usersToGenerate,
            incompleteResponse = isIncompleteResponse,
            items = userList
        )
    }

    private fun generateSearchUserRepositoriesResponse(
        reposToGenerate: Int,
        isIncompleteResponse: Boolean
    ): SearchUserRepositoriesResponse {
        val repoList = mutableListOf<SearchUserRepositoriesDetails>()

        (1..reposToGenerate).forEach { index ->
            repoList.add(getMockGitHubRepo(index))
        }

        return SearchUserRepositoriesResponse(
            totalCount = 1,
            incompleteResponse = isIncompleteResponse,
            items = repoList
        )
    }

    private fun getMockGitHubUser(identifier: Int): UserDetailsResponse {
        val username = "mockuser$identifier"

        return UserDetailsResponse(
            login = username,
            id = identifier,
            nodeId = "owner_id_index_$identifier=",
            avatarUrl = "https://via.placeholder.com/250x250.png/AEB1BF/00146E?text=$username",
            gravatarId = "",
            url = "https://api.github.com/users/$username",
            htmlUrl = "https://github.com/$username",
            followersUrl = "https://api.github.com/users/$username/followers",
            subscriptionsUrl = "https://api.github.com/users/$username/subscriptions",
            organizationsUrl = "https://api.github.com/users/$username/orgs",
            reposUrl = "https://api.github.com/users/$username/repos",
            receivedEventsUrl = "https://api.github.com/users/$username/received_events",
            type = "User",
            score = 1.0f,
            followingUrl = "https://api.github.com/users/$username/following{/other_user}",
            gistsUrl = "https://api.github.com/users/$username/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/$username/starred{/owner}{/repo}",
            eventsUrl = "https://api.github.com/users/$username/events{/privacy}",
            publicRepos = null,
            publicGists = null,
            followers = null,
            following = null,
            createdAt = null,

            updatedAt = null,
            name = null,
            bio = if (identifier % 2 == 0) null else "My Bio #$identifier",
            email = "$username@myEmail.com",
            location = null,
            siteAdmin = false,
            hireable = null,
            blog = if (identifier % 3 == 0) null else "My Blog #$identifier",
            company = if (identifier % 2 == 0) null else "Company #$identifier Inc.",
            suspendedAt = null,
        )
    }

    private fun getMockGitHubRepo(identifier: Int): SearchUserRepositoriesDetails {
        val username = "mockuser$identifier"
        val repoName = "MockRepo$identifier"

        return SearchUserRepositoriesDetails(
            id = identifier,
            name = repoName,
            fullName = "$username/$repoName",
            language = if (identifier % 3 == 0) "Java" else "Kotlin",
            starredCount = identifier + 1,
            watchersCount = identifier + 2,
            description = "Description $identifier",
            private = identifier % 2 == 0,
            fork = identifier % 2 == 0,
            htmlUrl = "https://github.com/$username/MockRepo$identifier"
        )
    }
}
