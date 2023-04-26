plugins {
    id("commons.android-library")
}
dependencies {
    implementation(project(BuildModules.Core.SYSTEMS))
    implementation(project(BuildModules.Core.ENTITIES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.provide.systems"
}
