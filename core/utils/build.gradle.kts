plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("java-test-fixtures")
}

dependencies {
    implementation(Deps.Coroutines.core)
    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)

    //TEST FIXTURES
    testFixturesApi(Deps.Test.JUnit5.api)
    testFixturesApi(Deps.Test.JUnit5.engine)
    testFixturesApi(Deps.Test.JUnit5.params)
    testFixturesApi(Deps.Test.assertJ)
    testFixturesApi(Deps.Test.mockk)
    testFixturesApi(Deps.Coroutines.test)

    testFixturesImplementation(kotlin("reflect"))
    testFixturesImplementation(Deps.Test.Jackson.core)
    testFixturesImplementation(Deps.Test.Jackson.databind)
    testFixturesImplementation(Deps.Test.Jackson.moduleKotlin)
    testFixturesImplementation(Deps.Test.Jackson.datatypeJsr310)
}