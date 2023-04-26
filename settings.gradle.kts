pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    plugins {
        id("com.android.application") version "7.1.0"
        id("com.android.library") version "7.1.0"
        id("org.jetbrains.kotlin.android") version "1.8.20"
        id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
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
