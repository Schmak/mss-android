@file:Suppress("UnstableApiUsage")

import Projects.Core
import Projects.Features

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = SdkVersions.compile

    defaultConfig {
        applicationId = "com.mss.app"
        minSdk = SdkVersions.min
        targetSdk = SdkVersions.target
        versionCode = AppVersion.code
        versionName = AppVersion.name

        testInstrumentationRunner = "com.mss.app.runner.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SERVER_URL", """"https://api.motorsportstats.com"""")
        buildConfigField("String", "API_KEY", """"${System.getenv("X_API_KEY")}"""")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles += listOf(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                File("${project.projectDir}/proguard-rules.pro")
            )
            signingConfig = signingConfigs.getByName("debug")
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
    packagingOptions {
        resources {
            excludes += listOf(
                "META-INF/{licenses/**,AL2.0,LGPL2.1}",
                "**/attach_hotspot_windows.dll",
            )
        }
    }
    lint {
        disable += setOf("IconLauncherShape", "IconMissingDensityFolder", "ObsoleteLintCustomCheck")
        warningsAsErrors = true
        abortOnError = true
    }
}

dependencies {
    implementation(project(Core.Network.CREDENTIALS))
    implementation(project(Core.UI))
    implementation(project(Features.SERIES))
    implementation(project(Features.DRIVER))
    implementation(project(Features.RESULTS))
    implementation(project(Features.TEAM))
    implementation(project(Features.VENUE))

    //Desugaring
    coreLibraryDesugaring(Deps.GradlePlugins.desugaring)

    //Core
    implementation(Deps.Coroutines.core)

    //AndroidX
    implementation(Deps.AndroidX.Accompanist.insets)
    implementation(Deps.AndroidX.Accompanist.systemUiController)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.lifecycleRuntime)
    implementation(Deps.AndroidX.lifecycleViewModel)

    //UI
    implementation(Deps.Compose.activity)

    //DI
    implementation(Deps.Hilt.Android.core)
    kapt(Deps.Hilt.compiler)

    //Logging
    implementation(Deps.Logging.timber)

    //Debug
    debugImplementation(Deps.leakCanary)

    //UNIT TESTS
    testImplementation(testFixtures(project(Core.UTILS)))

    //INSTRUMENTATION TESTS
    androidTestImplementation(Deps.AndroidX.Test.junit)
    androidTestImplementation(Deps.AndroidX.Test.espresso)
    androidTestImplementation(Deps.Compose.uiTest)
    androidTestImplementation(Deps.Test.assertJ)
    androidTestImplementation(Deps.Hilt.Android.testing)
    kaptAndroidTest(Deps.Hilt.Android.compiler)
}