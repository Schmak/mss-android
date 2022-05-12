import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Core.NETWORK_MODEL))

    implementation(Deps.Network.Retrofit.core)
    implementation(Deps.Network.Retrofit.gson)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}