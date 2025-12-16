package com.sample.fdelamora.samplearch.core.api.githubclient.requests

class GetSearchUserRepositoriesRequestParams(
    login: String
) : HashMap<String, String>() {
    init {
        put("q", "user:$login")
    }
}
