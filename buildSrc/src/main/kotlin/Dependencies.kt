object Dependencies {

    object Versions {
        // Kotlin
        // https://developer.android.com/jetpack/androidx/releases/compose-runtime
        // https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        const val COMPOSE_KOTLIN_COMPILER_EXTENSION = "1.4.6"
        const val ANDROIDX_COMPOSE_VERSION = "1.4.2"

        const val HILT = "2.45"
    }

    object Kotlin {
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Compose {
        const val UI = "androidx.compose.ui:ui:${Versions.ANDROIDX_COMPOSE_VERSION}"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.ANDROIDX_COMPOSE_VERSION}"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.ANDROIDX_COMPOSE_VERSION}"
        const val RUNTIME = "androidx.compose.runtime:runtime:${Versions.ANDROIDX_COMPOSE_VERSION}"
        const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.ANDROIDX_COMPOSE_VERSION}"

        const val ACTIVITY = "androidx.activity:activity-compose:1.3.1"
        const val NAVIGATION = "androidx.navigation:navigation-compose:2.5.3"
        const val MATERIAL = "androidx.compose.material:material:1.4.2"
        const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
        const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val BROWSER = "androidx.browser:browser:1.5.0"
    }


    object Accompanist {
        private const val ACCOMPANIST_VERSION = "0.30.1"
        const val SYSTEM_UI_CONTROLLER =
            "com.google.accompanist:accompanist-systemuicontroller:${ACCOMPANIST_VERSION}"
        const val NAVIGATION_ANIMATION =
            "com.google.accompanist:accompanist-navigation-animation:${ACCOMPANIST_VERSION}"
    }


    object AndroidX {
        private const val LIFECYCLE_VERSION = "2.6.1"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.6.1"
        const val LIFECYCLE_VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE_VERSION}"
        const val LIFECYCLE_RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${LIFECYCLE_VERSION}"

        const val CORE_KTX = "androidx.core:core-ktx:1.10.0"
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    }

    //Others

    private const val TIMBER_VERSION = "5.0.0-SNAPSHOT"
    const val TIMBER_ANDROID = "com.jakewharton.timber:timber-android:${TIMBER_VERSION}"
    const val TIMBER_JDK = "com.jakewharton.timber:timber-jdk:${TIMBER_VERSION}"

    const val RETROFIT_VERSION = "2.9.0"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:${RETROFIT_VERSION}"

    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:4.11.0"

    const val MOSHI_VERSION = "1.14.0"
    const val MOSHI = "com.squareup.moshi:moshi:${MOSHI_VERSION}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${MOSHI_VERSION}"

    private const val COIL_VERSION = "2.3.0"
    const val COIL = "io.coil-kt:coil:${COIL_VERSION}"
    const val COIL_COMPOSE_EXTENSIONS = "io.coil-kt:coil-compose:${COIL_VERSION}"

    const val ABOUT_LIBRARIES_UI = "com.mikepenz:aboutlibraries-compose:10.1.0" // Update buildSrc too

    const val LOTTIE = "com.airbnb.android:lottie-compose:6.0.0"

    /**
     * Project annotation processor dependencies, makes it easy to include external binaries or
     * other library modules to build.
     */
    object AnnotationProcessors {
        const val HILT_ANDROID = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT = "com.google.dagger:hilt-compiler:${Versions.HILT}"
        const val ROOM = "androidx.room:room-compiler:2.4.0"
    }


    object TestAndroidDependencies {
        const val ANNOTATION = "androidx.annotation:annotation:1.2.0"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object TestDependencies {
        const val JUPITER_VERSION = "5.9.2"
        const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${JUPITER_VERSION}"
        const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${JUPITER_VERSION}"
        const val JUPITER_PARAMS = "org.junit.jupiter:junit-jupiter-params:${JUPITER_VERSION}"

        const val MOCKITO = "org.mockito:mockito-core:5.3.1"
        const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:4.1.0"
    }
}
