import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Core.NETWORK_MODEL))
    testImplementation(project(Core.TEST_HELPERS))

    implementation(Deps.Network.okHttp)
    implementation(Deps.Network.Retrofit.core)
    implementation(Deps.Network.Retrofit.gson)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}