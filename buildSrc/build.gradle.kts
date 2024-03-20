plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {

    implementation("com.android.tools.build:gradle:8.2.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.51")
    implementation("com.mikepenz.aboutlibraries.plugin:aboutlibraries-plugin:10.1.0")
}
