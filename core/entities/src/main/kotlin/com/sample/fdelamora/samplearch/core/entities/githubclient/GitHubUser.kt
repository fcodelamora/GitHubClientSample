package com.sample.fdelamora.samplearch.core.entities.githubclient

import java.io.Serializable

data class GitHubUser(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val gravatarId: String?,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val reposUrl: String,
    val publicRepos: Int?,
    val publicGists: Int?,
    val followers: Int?,
    val following: Int?,
    val name: String?
) : Serializable {
    override fun equals(other: Any?): Boolean {
        val theOther = other as? GitHubUser ?: return false

        return this.id == theOther.id &&
            this.name == theOther.name
    }
}
