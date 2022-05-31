@file:Suppress("UnstableApiUsage")

import Projects.Core
import Projects.Domain

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
    implementation(project(Core.UTILS))
    implementation(project(Domain.MODEL))

    api(Deps.Compose.material)
    api(Deps.Compose.paging)
    api(Deps.Compose.ui)
    api(Deps.Compose.uiToolingPreview)
    api(Deps.UI.material)
    api(Deps.UI.coil)
    api(Deps.Hilt.navigationCompose)
    debugApi(Deps.Compose.uiTooling)

    //AndroidX
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.Accompanist.placeholder)
    implementation(Deps.AndroidX.Accompanist.insets)
    implementation(Deps.AndroidX.Accompanist.swipeRefresh)

    //Desugaring
    coreLibraryDesugaring(Deps.GradlePlugins.desugaring)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))
}