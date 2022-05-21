@file:Suppress("MissingPackageDeclaration")

object Projects {
    object Core {
        object Network {
            const val API = ":core:network:api"
            const val CREDENTIALS = ":core:network:credentials"
            const val MODEL = ":core:network:model"
        }

        const val UI = ":core:ui"
        const val UTILS = ":core:utils"
    }

    object Domain {
        const val MODEL = ":domain:model"
        const val REPOSITORIES = ":domain:repositories"
        const val TEST_HELPERS = ":domain:test-helpers"
    }

    object Features {
        const val SERIES = ":feature:series"
        const val TEAM = ":feature:team"
    }
}