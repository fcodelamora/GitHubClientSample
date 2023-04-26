plugins {
    id("commons.android-library-base")
}

dependencies {
    implementation(project(BuildModules.Core.ENTITIES))
    implementation(project(BuildModules.Core.USECASES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.common.resources"
}
