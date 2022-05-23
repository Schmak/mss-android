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
    implementation(project(Core.UI))
    implementation(project(Core.UTILS))
    implementation(project(Domain.REPOSITORIES))
    implementation(project(Domain.USE_CASES))

    implementation(Deps.Logging.timber)

    //AndroidX
    implementation(Deps.AndroidX.lifecycleViewModel)

    //Desugaring
    coreLibraryDesugaring(Deps.GradlePlugins.desugaring)

    //DI
    implementation(Deps.Hilt.Android.core)
    kapt(Deps.Hilt.compiler)
}