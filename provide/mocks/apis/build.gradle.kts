plugins {
    id("commons.api-handler")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.provide.mocks.api"
}
