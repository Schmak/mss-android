@file:Suppress("MissingPackageDeclaration", "StringLiteralDuplication")

object Versions {
    const val kotlin = "1.6.10"
    const val coroutines = "1.6.1"
    const val leakCanary = "2.9.1"

    object AndroidX {
        const val appcompat = "1.4.1"
        const val core = "1.7.0"
        const val lifecycle = "2.4.1"
        const val accompanist = "0.23.1"

        object Test {
            const val junit = "1.1.3"
            const val espresso = "3.4.0"
        }
    }

    object Compose {
        const val core = "1.1.1"
        const val activity = "1.4.0"
    }

    object GradlePlugin {
        const val android = "7.0.4"
        const val detekt = "1.20.0"
        const val desugaring = "1.1.5"
        const val dependencyUpdates = "0.42.0"
    }

    object Hilt {
        const val navigationCompose = "1.0.0"
        const val core = "2.41"
    }

    object Logging {
        const val timber = "5.0.1"
    }

    object Test {
        const val junit5 = "5.8.2"
        const val assertJ = "3.22.0"
        const val mockk = "1.12.4"
        const val jackson = "2.13.2"
    }

    object UI {
        const val material = "1.6.0"
        const val coil = "1.4.0"
    }
}