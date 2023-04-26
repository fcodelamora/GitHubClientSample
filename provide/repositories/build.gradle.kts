plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(BuildModules.Core.REPOSITORIES))
    implementation(project(BuildModules.Core.APIS))
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.DATASOURCES))

    // Via Hilt injection
    implementation(project(BuildModules.Provide.DATASOURCES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.provide.repositories"
}
