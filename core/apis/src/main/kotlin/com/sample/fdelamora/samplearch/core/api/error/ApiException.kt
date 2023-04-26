package com.sample.fdelamora.samplearch.core.api.error

import java.io.IOException

class ApiException(
    val statusCode: Int,
    val apiError: DefaultApiError? = null
) : IOException()
