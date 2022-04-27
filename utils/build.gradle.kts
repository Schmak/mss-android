import Projects.TEST_HELPERS

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(project(TEST_HELPERS))
}