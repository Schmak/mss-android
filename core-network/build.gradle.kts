import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Core.NETWORK_MODEL))
    api(project(Core.UTILS))
    api(project(Domain.MODEL))

    implementation(Deps.Network.Retrofit.core)
    implementation(Deps.Network.Retrofit.gson)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}