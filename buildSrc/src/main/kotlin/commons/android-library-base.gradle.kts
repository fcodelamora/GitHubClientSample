package commons

import Dependencies

/**
 * Base Gradle file used by all com.android.library Gradle files to reduce repetition of shared
 * attributes.
 */
plugins {
    id("com.android.library")
    id("commons.android-shared-dependencies")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidBuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidBuildConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }

    kotlinOptions {
        jvmTarget = "19"
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.COMPOSE_KOTLIN_COMPILER_EXTENSION
    }

}

dependencies {
    implementation(Dependencies.AndroidX.HILT)
    kapt(Dependencies.AnnotationProcessors.HILT)
    kapt(Dependencies.AnnotationProcessors.HILT_ANDROID)
}