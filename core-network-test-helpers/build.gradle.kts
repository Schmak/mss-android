import Projects.Core

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(Core.NETWORK_MODEL))
}