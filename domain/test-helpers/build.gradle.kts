import Projects.Core
import Projects.Domain

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(testFixtures(project(Core.UTILS)))
    implementation(project(Domain.MODEL))
}