package com.sample.fdelamora.samplearch.core.api.githubclient

import com.sample.fdelamora.samplearch.core.api.ErrorResponseInterceptor
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUserRepositoriesRequestParams
import com.sample.fdelamora.samplearch.core.api.githubclient.requests.GetSearchUsersRequestParams
import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUserRepositoriesResponse
import com.sample.fdelamora.samplearch.core.api.githubclient.response.SearchUsersResponse
import com.sample.fdelamora.samplearch.core.api.githubclient.response.UserDetailsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IGitHubApi {

    @GET("users/{username}")
    suspend fun userDetails(
        @Path("username") username: String
    ): UserDetailsResponse

    @GET("search/users")
    suspend fun searchUsers(
        @QueryMap(encoded = true) requestParams: GetSearchUsersRequestParams
    ): SearchUsersResponse

    @GET("search/repositories")
    suspend fun searchUserRepositories(
        @QueryMap(encoded = true) requestParams: GetSearchUserRepositoriesRequestParams
    ): SearchUserRepositoriesResponse

    companion object {
        fun provide(
            baseUrl: String,
            apiKey: String,
            isOutputEnabled: Boolean,
        ): IGitHubApi {
            val builder = OkHttpClient.Builder()
                .addInterceptor(GitHubRequestInterceptor(apiKey))
                .addInterceptor(ErrorResponseInterceptor())
            if (isOutputEnabled) {
                builder.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            val httpClient = builder.build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(IGitHubApi::class.java)
        }
    }
}
