@file:Suppress("UnstableApiUsage")

import Projects.Core

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
        buildConfigField("String", "API_KEY", """"${System.getenv("x-api-key")}"""")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles += listOf(
                getDefaultProguardFile("proguard-android.txt"),
                File("proguard-rules.pro")
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
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    implementation(project(Core.NETWORK))
    implementation(project(Core.UI))
    implementation(project(Core.UTILS))
    testImplementation(project(Core.TEST_HELPERS))

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
    implementation(Deps.Hilt.navigationCompose)

    //Logging
    implementation(Deps.Logging.timber)

    //Debug
    debugImplementation(Deps.leakCanary)

    //Integration tests
    androidTestImplementation(Deps.AndroidX.Test.junit)
    androidTestImplementation(Deps.AndroidX.Test.espresso)
    androidTestImplementation(Deps.Compose.uiTest)
    androidTestImplementation(Deps.Test.assertJ)
    androidTestImplementation(Deps.Hilt.Android.testing)
    kaptAndroidTest(Deps.Hilt.Android.compiler)
}