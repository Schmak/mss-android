import Projects.Core

plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(project(Core.TEST_HELPERS))
}