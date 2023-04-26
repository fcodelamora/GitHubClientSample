package com.sample.fdelamora.samplearch.core.repository.extensions

import com.sample.fdelamora.samplearch.core.api.error.ApiException
import com.sample.fdelamora.samplearch.core.entities.exception.AppException

/**
 * Default Behavior for General API Exceptions
 */
fun ApiException.toApplicationException(): AppException {
    // Different kinds of exceptions are expected to be added and handled later
    apiError?.let {
        return AppException.GeneralApiException(
            messageFromApi = "${it.errorMessage}\n" +
                "${it.details?.firstOrNull()?.errorMessage}",
            errorCode = statusCode
        )
    }

    return AppException.UnknownException
}
