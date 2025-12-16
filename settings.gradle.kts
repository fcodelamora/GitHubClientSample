pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
        google()
    }
    plugins {
        id("com.android.application") version "8.13.2"
        id("com.android.library") version "8.13.2"
        id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
        id("org.jetbrains.kotlin.android") version "2.2.21" // Kotlin Lang
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "SampleArchitecture"

include(
    ":app",
    // Core
    ":core:apis",
    ":core:datasources",
    ":core:entities",
    ":core:repositories",
    ":core:systems",
    ":core:usecases",
    // Provide
    ":provide:apis",
    ":provide:repositories",
    ":provide:datasources",
    ":provide:systems",
    // Mocks
    ":provide:mocks:apis",
    // Features
    ":features:githubclient",
    // Common
    ":common:resources",
    ":common:di",
)
