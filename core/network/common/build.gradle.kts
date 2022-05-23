import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("java-test-fixtures")
}

dependencies {
    implementation(project(Core.Network.CREDENTIALS))

    implementation(Deps.Network.okHttp)
    implementation(Deps.Network.Retrofit.core)
    implementation(Deps.Network.Retrofit.gson)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //TEST FIXTURES
    testFixturesImplementation(project(Core.Network.CREDENTIALS))
    testFixturesImplementation(Deps.Network.Retrofit.core)
    testFixturesImplementation(Deps.Network.Retrofit.gson)
}