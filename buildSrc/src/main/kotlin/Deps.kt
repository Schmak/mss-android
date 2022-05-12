@file:Suppress("MissingPackageDeclaration")

object Deps {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"

        object Accompanist {
            const val prefix = "com.google.accompanist:accompanist"
            const val insets = "$prefix-insets:${Versions.AndroidX.accompanist}"
            const val systemUiController = "$prefix-systemuicontroller:${Versions.AndroidX.accompanist}"
        }

        object Test {
            const val junit = "androidx.test.ext:junit:${Versions.AndroidX.Test.junit}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
        }
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val material = "androidx.compose.material:material:${Versions.Compose.core}"
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.core}"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.core}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.core}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.core}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object GradlePlugins {
        const val android = "com.android.tools.build:gradle:${Versions.GradlePlugin.android}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.core}"
        const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.GradlePlugin.desugaring}"
        const val dependencyUpdates = "com.github.ben-manes.versions"

        object Detekt {
            const val core = "io.gitlab.arturbosch.detekt"
            const val formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.GradlePlugin.detekt}"
        }
    }

    object Hilt {
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.Hilt.core}"
        const val core = "com.google.dagger:hilt-core:${Versions.Hilt.core}"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.Hilt.navigationCompose}"

        object Android {
            const val core = "com.google.dagger:hilt-android:${Versions.Hilt.core}"
            const val testing = "com.google.dagger:hilt-android-testing:${Versions.Hilt.core}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt.core}"
        }
    }

    object Kotlin {
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:${Versions.Logging.timber}"
    }

    object Network {
        object Retrofit {
            const val core = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
            const val gson = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
        }
    }

    object Test {
        object JUnit5 {
            const val api = "org.junit.jupiter:junit-jupiter-api:${Versions.Test.junit5}"
            const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.Test.junit5}"
            const val params = "org.junit.jupiter:junit-jupiter-params:${Versions.Test.junit5}"
        }

        object Jackson {
            const val core = "com.fasterxml.jackson.core:jackson-core:${Versions.Test.jackson}"
            const val databind = "com.fasterxml.jackson.core:jackson-databind:${Versions.Test.jackson}"
            const val moduleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.Test.jackson}"
        }

        const val assertJ = "org.assertj:assertj-core:${Versions.Test.assertJ}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
    }

    object UI {
        const val material = "com.google.android.material:material:${Versions.UI.material}"
        const val coil = "io.coil-kt:coil-compose:${Versions.UI.coil}"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}