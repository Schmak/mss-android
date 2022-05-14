@file:Suppress("UnstableApiUsage")

import Projects.Core

plugins {
    id("com.android.library")
    kotlin("android")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.core
    }
}

dependencies {
    implementation(project(Core.UTILS))

    api(Deps.Compose.material)
    api(Deps.Compose.paging)
    api(Deps.Compose.ui)
    api(Deps.Compose.uiToolingPreview)
    api(Deps.UI.material)
    api(Deps.UI.coil)
    debugApi(Deps.Compose.uiTooling)
}