import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Domain.MODEL))
    implementation(project(Core.Network.Api.V3))
    implementation(project(Core.UTILS))

    implementation(Deps.Coroutines.core)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
    testImplementation(testFixtures(project(Core.Network.Api.V3)))
    testImplementation(testFixtures(project(Domain.MODEL)))
}