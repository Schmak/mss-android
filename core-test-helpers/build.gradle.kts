import Projects.Core

plugins {
    kotlin("jvm")
}

dependencies {
    api(Deps.Test.JUnit5.api)
    api(Deps.Test.JUnit5.engine)
    api(Deps.Test.JUnit5.params)
    api(Deps.Test.assertJ)
    api(Deps.Test.mockk)

    implementation(project(Core.UTILS))
    implementation(kotlin("reflect"))
    implementation(Deps.Test.Jackson.core)
    implementation(Deps.Test.Jackson.databind)
    implementation(Deps.Test.Jackson.moduleKotlin)
}