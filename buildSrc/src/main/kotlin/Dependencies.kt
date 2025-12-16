object Dependencies {

    object Versions {

        // Kotlin
        // https://developer.android.com/jetpack/androidx/releases/compose-runtime
        // https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        const val COMPOSE_KOTLIN_COMPILER_EXTENSION = "1.10.0"


        const val HILT = "2.57.2"
    }

    object Kotlin {
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2"
    }

    object Compose {
        const val COMPOSE_BOM = "androidx.compose:compose-bom:2025.12.00"

        const val ICONS_EXTENDED = "androidx.compose.material:material-icons-extended-android"
        const val UI = "androidx.compose.ui:ui"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val MATERIAL = "androidx.compose.material:material"
        const val RUNTIME = "androidx.compose.runtime:runtime"
        const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata"

        const val ACTIVITY = "androidx.activity:activity-compose:1.12.1"
        const val NAVIGATION = "androidx.navigation:navigation-compose:2.9.6"
        const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0"
        const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:1.3.0"
    }

    object AndroidX {
        private const val LIFECYCLE_VERSION = "2.10.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.7.1"
        const val LIFECYCLE_VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE_VERSION}"
        const val LIFECYCLE_RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${LIFECYCLE_VERSION}"

        const val CORE_KTX = "androidx.core:core-ktx:1.17.0"
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val BROWSER = "androidx.browser:browser:1.9.0"
    }

    //Others
    const val KERMIT = "co.touchlab:kermit:2.0.8"

    const val RETROFIT_VERSION = "3.0.0"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:${RETROFIT_VERSION}"

    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:5.3.2"

    const val MOSHI_VERSION = "1.15.1"
    const val MOSHI = "com.squareup.moshi:moshi:${MOSHI_VERSION}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${MOSHI_VERSION}"

    private const val COIL_VERSION = "2.7.0"
    const val COIL = "io.coil-kt:coil:${COIL_VERSION}"
    const val COIL_COMPOSE_EXTENSIONS = "io.coil-kt:coil-compose:${COIL_VERSION}"

    const val ABOUT_LIBRARIES_UI = "com.mikepenz:aboutlibraries-compose:13.1.0" // Update buildSrc too

    const val LOTTIE = "com.airbnb.android:lottie-compose:6.7.1"

    /**
     * Project annotation processor dependencies, makes it easy to include external binaries or
     * other library modules to build.
     */
    object AnnotationProcessors {
        const val HILT_ANDROID = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    }


    object TestAndroidDependencies {
        const val ESPRESSO = "androidx.test.espresso:espresso-core:3.7.0"
    }

    object TestDependencies {
        const val JUPITER_VERSION = "5.10.2"
        const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${JUPITER_VERSION}"
        const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${JUPITER_VERSION}"
        const val JUPITER_PARAMS = "org.junit.jupiter:junit-jupiter-params:${JUPITER_VERSION}"

        const val MOCKITO = "org.mockito:mockito-core:5.21.0"
        const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:6.1.0"
    }
}
