plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    // Adding a plugin?
    // It should be done in the dependencies block for buildSrc, check KSP as an example
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
    maven("https://plugins.gradle.org/m2/")
}
private object PluginsVersions {
    const val GRADLE_ANDROID = "8.13.2"
    const val KOTLIN = "2.2.21"
    const val HILT = "2.57.2"
    const val ABOUT_LIBRARIES = "13.1.0"
    const val KSP = "2.3.3"
}

dependencies {

    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")

    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
    implementation("com.mikepenz.aboutlibraries.plugin:aboutlibraries-plugin:${PluginsVersions.ABOUT_LIBRARIES}")

    implementation("org.jetbrains.kotlin.plugin.compose:org.jetbrains.kotlin.plugin.compose.gradle.plugin:${PluginsVersions.KOTLIN}")
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:${PluginsVersions.KSP}")
}
