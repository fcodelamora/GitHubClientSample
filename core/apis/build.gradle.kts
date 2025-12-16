plugins {
    id("commons.kotlin-library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)

    implementation(Dependencies.OKHTTP_LOGGING)

    implementation(Dependencies.MOSHI)
    ksp(Dependencies.MOSHI_CODEGEN)

    // Testing
    kspTest(Dependencies.MOSHI_CODEGEN)
}
