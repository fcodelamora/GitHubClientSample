package com.sample.fdelamora.samplearch.core.api.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultApiError(
    @field:Json(name = "message")
    val errorMessage: String?,

    @field:Json(name = "errors")
    val details: List<ApiErrorDetails>? = null
)

@JsonClass(generateAdapter = true)
data class ApiErrorDetails(
    @field:Json(name = "message")
    val errorMessage: String?,
)
