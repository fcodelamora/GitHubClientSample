package com.sample.fdelamora.samplearch.core.api.githubclient.requests

class GetSearchUsersRequestParams(
    keywords: String
) : HashMap<String, String>() {
    init {
        put("q", keywords)
    }
}
