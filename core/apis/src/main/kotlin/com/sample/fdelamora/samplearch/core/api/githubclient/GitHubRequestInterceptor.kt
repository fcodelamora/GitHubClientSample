package com.sample.fdelamora.samplearch.core.api.githubclient

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import timber.log.debug

class GitHubRequestInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .method(original.method, original.body)
            .addHeader("Accept", "application/vnd.github.v3+json") // Use API v3
            .addHeader("Authorization", "Bearer $apiKey")
        Timber.debug { request.toString() }
        return chain.proceed(request.build())
    }
}
