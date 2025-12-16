package com.sample.fdelamora.samplearch.provide.mocks.api.debugflags

import com.sample.fdelamora.samplearch.core.api.error.ApiErrorDetails
import com.sample.fdelamora.samplearch.core.api.error.ApiException
import com.sample.fdelamora.samplearch.core.api.error.DefaultApiError
import java.net.UnknownHostException
import javax.inject.Singleton

@Singleton
data class MockDebugFlags(
    var delayInMillis: Long = 250,
    var apiErrorType: ApiErrorType = ApiErrorType.NoError,
    var userCount: Int = 10,
    var repoCount: Int = 5,
    var isIncompleteResponse: Boolean = true
)

enum class ApiErrorType(val displayName: String) {
    NoError("No Error"),
    UnknownHostError("Could not reach the server"),
    Error("Generic error"),
    UnknownError("Unknown Error");

    fun handleError() {
        when (this) {
            NoError -> return
            UnknownHostError -> throw UnknownHostException()
            Error -> throw ApiException(
                statusCode = 422,
                apiError = DefaultApiError(
                    errorMessage = "Default Error",
                    details = listOf(
                        ApiErrorDetails(errorMessage = "This is a mock error")
                    )
                )
            )

            UnknownError -> throw ApiException(
                statusCode = 500,
                apiError = DefaultApiError(
                    errorMessage = "Unknown Error",
                    details = listOf(
                        ApiErrorDetails(errorMessage = "This is a mock unknown error")
                    )
                )
            )
        }
    }

    fun toIndex(): Int {
        for (errorType in values()) {
            if (this == errorType) {
                return errorType.ordinal
            }
        }
        return 0
    }

    companion object {
        fun fromIndex(index: Int): ApiErrorType = values().first { it.ordinal == index }
    }
}
