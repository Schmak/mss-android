import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Domain.MODEL))
    implementation(project(Core.Network.API))
    implementation(project(Core.Network.MODEL))
    implementation(project(Core.UTILS))

    implementation(Deps.Coroutines.core)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
    testImplementation(testFixtures(project(Core.Network.MODEL)))
    testImplementation(testFixtures(project(Domain.MODEL)))
}