package com.sample.fdelamora.samplearch.core.api.githubclient.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchUserRepositoriesResponse(
    @field:Json(name = "total_count")
    val totalCount: Int,

    @field:Json(name = "incomplete_results")
    val incompleteResponse: Boolean,

    @field:Json(name = "items")
    val items: List<SearchUserRepositoriesDetails>
)

@JsonClass(generateAdapter = true)
data class SearchUserRepositoriesDetails(

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "full_name")
    val fullName: String,

    @field:Json(name = "language")
    val language: String?,

    @field:Json(name = "stargazers_count")
    val starredCount: Int,

    @field:Json(name = "watchers_count")
    val watchersCount: Int,

    @field:Json(name = "description")
    val description: String?,

    @field:Json(name = "private")
    val private: Boolean,

    @field:Json(name = "fork")
    val fork: Boolean,

    @field:Json(name = "html_url")
    val htmlUrl: String

)
