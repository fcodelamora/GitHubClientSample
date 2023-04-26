plugins {
    id("commons.android-library-base")
}

dependencies {
    implementation(project(BuildModules.Core.REPOSITORIES))
    implementation(project(BuildModules.Core.SYSTEMS))
    implementation(project(BuildModules.Core.USECASES))
}
android {
    namespace = "com.sample.fdelamora.samplearch.common.di"
}
