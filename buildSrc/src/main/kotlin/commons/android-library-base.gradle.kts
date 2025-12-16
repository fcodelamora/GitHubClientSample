package commons

import AndroidBuildConfig
import Dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

/**
 * Base Gradle file used by all com.android.library Gradle files to reduce repetition of shared
 * attributes.
 */
plugins {
    id("com.android.library")
    id("commons.android-shared-dependencies")
    kotlin("android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = AndroidBuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidBuildConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    android.sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.COMPOSE_KOTLIN_COMPILER_EXTENSION
    }

    kotlin {
        jvmToolchain(21)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

}

dependencies {
    implementation(Dependencies.AndroidX.HILT)
    ksp(Dependencies.AnnotationProcessors.HILT)
    ksp(Dependencies.AnnotationProcessors.HILT_ANDROID)
}
