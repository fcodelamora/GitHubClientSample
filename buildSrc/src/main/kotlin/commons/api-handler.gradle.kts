package commons

import AndroidBuildConfig
import BuildModules

/**
 * Base Gradle file used by all API related Gradle files to reduce repetition of shared
 * attributes. This file also provides additional BuildConfig fields required to initialize the
 * APIs
 */
plugins {
    id("commons.android-library-base")
}

android {
    val baseUrlGithubKey = "API_BASE_URL_GITHUB"
    val baseUrlGithubValue = "\"https://api.github.com/\""

    val apiKeyGitHubKey = "API_KEY_GITHUB"
    val apiKeyGitHubLocalProperty = "apiKeys.githubApiKey"

    flavorDimensions += setOf(AndroidBuildConfig.ProductDimensions.ENVIRONMENT)

    productFlavors {
        create("product") {
            dimension = AndroidBuildConfig.ProductDimensions.ENVIRONMENT
            buildConfigField("String", baseUrlGithubKey, baseUrlGithubValue)
            buildConfigField(
                "String",
                apiKeyGitHubKey,
                utils.getLocalProperty(apiKeyGitHubLocalProperty, project)
            )
        }
        create("qa") {
            dimension = AndroidBuildConfig.ProductDimensions.ENVIRONMENT
            buildConfigField("String", baseUrlGithubKey, baseUrlGithubValue)
            buildConfigField(
                "String",
                apiKeyGitHubKey,
                utils.getLocalProperty(apiKeyGitHubLocalProperty, project)
            )
        }
        create("dev") {
            dimension = AndroidBuildConfig.ProductDimensions.ENVIRONMENT
            buildConfigField("String", baseUrlGithubKey, baseUrlGithubValue)
            buildConfigField(
                "String",
                apiKeyGitHubKey,
                utils.getLocalProperty(apiKeyGitHubLocalProperty, project)
            )
        }
    }
}

dependencies {
    implementation(project(BuildModules.Core.APIS))
}