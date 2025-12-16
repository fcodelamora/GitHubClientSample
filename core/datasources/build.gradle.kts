plugins {
    id("commons.kotlin-library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
}
