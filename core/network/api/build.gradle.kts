import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Core.Network.CREDENTIALS))
    implementation(project(Core.Network.MODEL))

    implementation(Deps.Network.okHttp)
    implementation(Deps.Network.Retrofit.core)
    implementation(Deps.Network.Retrofit.gson)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
}