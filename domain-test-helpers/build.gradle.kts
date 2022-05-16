import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(Core.TEST_HELPERS))
    implementation(project(Domain.MODEL))
}