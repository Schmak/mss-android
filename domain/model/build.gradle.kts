import Projects.Core

plugins {
    kotlin("jvm")
}

dependencies {
    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
}