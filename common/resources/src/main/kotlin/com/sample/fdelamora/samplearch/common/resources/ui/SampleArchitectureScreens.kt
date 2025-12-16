package com.sample.fdelamora.samplearch.common.resources.ui

sealed class SampleArchitectureScreens(val name: String) {
    sealed class GitHubClient(name: String) : SampleArchitectureScreens(name) {
        object SearchUsers : GitHubClient("search_users")
        object UserRepos : GitHubClient("user_repos")
    }

    override fun equals(other: Any?): Boolean = this.name == (other as? SampleArchitectureScreens)?.name
}
