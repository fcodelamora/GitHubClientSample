package com.sample.fdelamora.samplearch.core.usecases.test.githubclient

import com.sample.fdelamora.samplearch.core.entities.exception.AppException
import com.sample.fdelamora.samplearch.core.entities.githubclient.GitHubUser
import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.ISearchUsersUseCaseView
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.SearchUsersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.given
import org.mockito.kotlin.willAnswer

class SearchUsersUseCaseTest {

    private lateinit var mockView: ISearchUsersUseCaseView
    private lateinit var useCase: SearchUsersUseCase
    private lateinit var mockRepository: IGitHubRepository

    private val mockList = listOf(
        makeMockSearchGithubUser(0),
        makeMockSearchGithubUser(1),
        makeMockSearchGithubUser(2)
    )

    // Simulate the incomplete user information received from search/user
    private fun makeMockSearchGithubUser(index: Int = 1): GitHubUser =
        mockGitHubUser(index).copy(
            name = null,
            followers = null,
            following = null
        )

    @BeforeEach
    fun before() = runBlocking {
        mockRepository = Mockito.mock(IGitHubRepository::class.java).also {
            given(
                it.searchUsers(any())
            ) willAnswer { mockList }
        }
        mockView = Mockito.mock(ISearchUsersUseCaseView::class.java)
        useCase = SearchUsersUseCase(mockView, mockRepository)
    }

    @Test
    fun `execute - success`() = runBlocking {
        useCase.execute("username")

        Mockito.verify(mockView, Mockito.times(1)).onUsersFound(mockList)
        Mockito.verify(mockView, Mockito.times(1)).showProgressView()
        Mockito.verify(mockView, Mockito.times(1)).hideProgressView()
        Mockito.verify(mockView, Mockito.never()).handleException(anyOrNull())
    }

    @Test
    fun `execute - error - user data throws exception`() = runBlocking {
        given(mockRepository.searchUsers(any())) willAnswer {
            throw AppException.GeneralApiException(messageFromApi = "", errorCode = 0)
        }

        useCase.execute("username")

        Mockito.verify(mockView, Mockito.times(1)).onUsersFound(emptyList())
        Mockito.verify(mockView, Mockito.times(1)).showProgressView()
        Mockito.verify(mockView, Mockito.times(1)).hideProgressView()
        Mockito.verify(mockView, Mockito.times(1)).handleException(anyOrNull())
    }
}
