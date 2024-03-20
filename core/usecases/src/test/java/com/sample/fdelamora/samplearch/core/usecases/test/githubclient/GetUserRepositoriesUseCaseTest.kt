package com.sample.fdelamora.samplearch.core.usecases.test.githubclient

import com.sample.fdelamora.samplearch.core.entities.exception.AppException
import com.sample.fdelamora.samplearch.core.repository.githubclient.IGitHubRepository
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.GetUserRepositoriesUseCase
import com.sample.fdelamora.samplearch.core.usecases.feature.githubclient.IGetUserRepositoriesView
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.given
import org.mockito.kotlin.willAnswer

/**
 * All test assume a calendar where the week start day is WeekDay.MONDAY
 */
class GetUserRepositoriesUseCaseTest {

    private lateinit var mockView: IGetUserRepositoriesView
    private lateinit var useCase: GetUserRepositoriesUseCase
    private lateinit var mockRepository: IGitHubRepository

    // Simulate the incomplete user information received from search/user
    private val mockUser = mockGitHubUser().copy(
        name = null,
        followers = null,
        following = null
    )

    private val mockRepos = listOf(
        mockGitHubRepo(0),
        mockGitHubRepo(1).copy(fork = true),
        mockGitHubRepo(2)
    )

    @BeforeEach
    fun before() = runBlocking {
        mockRepository = mock(IGitHubRepository::class.java).also {
            given(
                it.searchUserRepositories(any())
            ) willAnswer { mockRepos }
        }
        mockView = mock(IGetUserRepositoriesView::class.java)
        useCase = GetUserRepositoriesUseCase(mockView, mockRepository)
    }

    @Test
    fun `execute - success`() = runBlocking {
        val updatedUser = mockGitHubUser()
        val mockReposWithNoForks = listOf(mockRepos[0], mockRepos[2])

        given(mockRepository.userDetails(any())) willAnswer { updatedUser }

        useCase.execute(mockUser)

        // Do not
        verify(mockView, never()).onUserRepositoriesReceived(updatedUser, mockRepos)
        // Call with mockRepos that include forks
        verify(mockView, times(1))
            .onUserRepositoriesReceived(updatedUser, mockReposWithNoForks)

        verify(mockView, times(1)).showProgressView()
        verify(mockView, times(1)).hideProgressView()
        verify(mockView, never()).handleException(anyOrNull())
    }

    @Test
    fun `execute - error - user data throws exception`() = runBlocking {
        given(mockRepository.userDetails(any())) willAnswer {
            throw AppException.GeneralApiException(messageFromApi = "", errorCode = 0)
        }

        useCase.execute(mockUser)

        verify(mockView, times(1)).onUserRepositoriesReceived(mockUser, emptyList())
        verify(mockView, times(1)).showProgressView()
        verify(mockView, times(1)).hideProgressView()
        verify(mockView, times(1)).handleException(anyOrNull())
    }

    @Test
    fun `execute - error - repository data throws exception`() = runBlocking {
        given(mockRepository.searchUserRepositories(any())) willAnswer {
            throw AppException.GeneralApiException(messageFromApi = "", errorCode = 0)
        }

        useCase.execute(mockUser)

        verify(mockView, times(1)).onUserRepositoriesReceived(mockUser, emptyList())
        verify(mockView, times(1)).showProgressView()
        verify(mockView, times(1)).hideProgressView()
        verify(mockView, times(1)).handleException(anyOrNull())
    }
}
