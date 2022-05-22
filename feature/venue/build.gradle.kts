@file:Suppress("UnstableApiUsage")

import Projects.Core
import Projects.Domain

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = SdkVersions.compile

    defaultConfig {
        minSdk = SdkVersions.min
        targetSdk = SdkVersions.target

        testInstrumentationRunner = "com.mss.app.runner.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(Core.UI))
    implementation(project(Core.UTILS))
    implementation(project(Domain.REPOSITORIES))

    implementation(Deps.Logging.timber)

    //DI
    implementation(Deps.Hilt.Android.core)
    kapt(Deps.Hilt.compiler)
}