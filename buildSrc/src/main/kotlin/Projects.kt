@file:Suppress("MissingPackageDeclaration")

object Projects {
    object Core {
        const val NETWORK = ":core:network"
        const val NETWORK_MODEL = ":core:network-model"
        const val NETWORK_TEST_HELPERS = ":core:network-test-helpers"
        const val TEST_HELPERS = ":core:test-helpers"
        const val UI = ":core:ui"
        const val UTILS = ":core:utils"
    }

    object Domain {
        const val MODEL = ":domain-model"
        const val TEST_HELPERS = ":domain-test-helpers"
    }

    object Features {
        const val SERIES = ":feature-series"
    }
}