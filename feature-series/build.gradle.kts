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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.core
    }
}

dependencies {
    implementation(project(Core.NETWORK))
    implementation(project(Core.UI))
    implementation(project(Core.UTILS))
    implementation(project(Domain.MODEL))

    testImplementation(project(Core.TEST_HELPERS))
    testImplementation(project(Core.NETWORK_TEST_HELPERS))
    testImplementation(project(Domain.TEST_HELPERS))

    implementation(Deps.Coroutines.core)

    //Desugaring
    coreLibraryDesugaring(Deps.GradlePlugins.desugaring)

    //DI
    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}