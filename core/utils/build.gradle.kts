import Projects.Core

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(Deps.Coroutines.core)
    testImplementation(project(Core.TEST_HELPERS))
    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}