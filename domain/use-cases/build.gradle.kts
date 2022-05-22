import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(project(Domain.MODEL))
    implementation(project(Core.UTILS))
    implementation(project(Domain.REPOSITORIES))

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}