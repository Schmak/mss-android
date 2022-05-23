import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("java-test-fixtures")
}

dependencies {
    implementation(project(Core.Network.COMMON))

    implementation(Deps.Network.Retrofit.core)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
    testImplementation(testFixtures(project(Core.Network.COMMON)))
}