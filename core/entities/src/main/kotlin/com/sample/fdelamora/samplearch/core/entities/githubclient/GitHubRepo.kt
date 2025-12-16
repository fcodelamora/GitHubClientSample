package com.sample.fdelamora.samplearch.core.entities.githubclient

import java.io.Serializable

data class GitHubRepo(
    val id: Int,
    val name: String,
    val fullName: String,
    val language: String?,
    val starredCount: Int,
    val followersCount: Int,
    val description: String?,
    val private: Boolean,
    val fork: Boolean,
    val htmlUrl: String
) : Serializable {
    override fun equals(other: Any?): Boolean = this.id == (other as? GitHubRepo)?.id
}
