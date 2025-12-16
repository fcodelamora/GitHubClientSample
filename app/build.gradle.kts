import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("commons.android-shared-dependencies")
    kotlin("android")
    id("com.mikepenz.aboutlibraries.plugin")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.sample.fdelamora.samplearch"
    compileSdk = AndroidBuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = AndroidBuildConfig.APPLICATION_ID

        minSdk = AndroidBuildConfig.MIN_SDK_VERSION
        targetSdk = AndroidBuildConfig.TARGET_SDK_VERSION
        versionCode = AndroidBuildConfig.VERSION_CODE
        versionName = AndroidBuildConfig.VERSION_NAME
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../android_debug.jks")
            keyAlias = "androiddebugkey"
            storePassword = "android"
            keyPassword = "android"
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    android.sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }

    buildTypes {
        getByName("release") {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isDebuggable = false
            isMinifyEnabled = true
        }

        getByName("debug") {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isDebuggable = true
            isMinifyEnabled = false
        }
    }

    flavorDimensions += setOf(AndroidBuildConfig.ProductDimensions.ENVIRONMENT)

    productFlavors {

        create("product") {
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            signingConfig = signingConfigs.getByName("debug")
        }
        create("dev") {
            applicationIdSuffix = ".dev"
            // resConfigs("en", "xxhdpi")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.COMPOSE_KOTLIN_COMPILER_EXTENSION
    }

    hilt {
        enableAggregatingTask = true
    }

    kotlin {
        jvmToolchain(21)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.USECASES))
    // implementation(project(BuildModules.Core.SYSTEMS)) currently, no systems in use
    // implementation(project(BuildModules.Core.DATASOURCES)) currently, no DATASOURCES in use

    implementation(project(BuildModules.Common.RESOURCES))
    implementation(project(BuildModules.Common.DEPENDENCY_INJECTION))

    implementation(project(BuildModules.Features.GITHUB_CLIENT))

    implementation(Dependencies.Compose.HILT_NAVIGATION)
    implementation(Dependencies.Compose.ACTIVITY)

    implementation(Dependencies.ABOUT_LIBRARIES_UI)

    implementation(Dependencies.AndroidX.HILT)
    ksp(Dependencies.AnnotationProcessors.HILT)
    ksp(Dependencies.AnnotationProcessors.HILT_ANDROID)

    // Required by Hilt
    implementation(project(BuildModules.Provide.REPOSITORIES))
    // implementation(project(BuildModules.Provide.SYSTEMS)) currently, no systems in use
    // implementation(project(BuildModules.Provide.DATASOURCES)) currently, no DATASOURCES in use

    // Differentiate between real and mock dependencies.
    "productImplementation"(project(BuildModules.Provide.APIS))
    "qaImplementation"(project(BuildModules.Provide.APIS))
    "devImplementation"(project(BuildModules.Provide.Mocks.APIS))
}
