package com.sample.fdelamora.samplearch.core.api.githubclient.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchUsersResponse(
    @field:Json(name = "total_count")
    val totalCount: Int,

    @field:Json(name = "incomplete_results")
    val incompleteResponse: Boolean,

    @field:Json(name = "items")
    val items: List<UserDetailsResponse>
)
