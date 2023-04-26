plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.DATASOURCES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.provide.datasources"
}
