import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Domain.MODEL))
    implementation(project(Core.NETWORK))
    implementation(project(Core.UTILS))
    testImplementation(project(Core.NETWORK_TEST_HELPERS))
    testImplementation(project(Core.TEST_HELPERS))
    testImplementation(project(Domain.TEST_HELPERS))

    implementation(Deps.Coroutines.core)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}