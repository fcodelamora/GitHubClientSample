plugins {
    id("commons.kotlin-library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.APIS))
    implementation(project(BuildModules.Core.DATASOURCES))
}
