import Projects.Core

plugins {
    kotlin("jvm")
    id("java-test-fixtures")
}

dependencies {
    //TEST FIXTURES
    testFixturesImplementation(testFixtures(project(Core.UTILS)))

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
}