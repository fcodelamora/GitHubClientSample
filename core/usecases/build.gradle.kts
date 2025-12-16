plugins {
    id("commons.kotlin-library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.REPOSITORIES))
    implementation(project(BuildModules.Core.SYSTEMS))
}
