@file:Suppress("MissingPackageDeclaration")

object Projects {
    object Core {
        object Network {
            const val API = ":core:network:api"
            const val COMMON = ":core:network:common"
            const val CREDENTIALS = ":core:network:credentials"
            const val MODEL = ":core:network:model"
        }

        const val UI = ":core:ui"
        const val UTILS = ":core:utils"
    }

    object Domain {
        const val MODEL = ":domain:model"
        const val REPOSITORIES = ":domain:repositories"
        const val USE_CASES = ":domain:use-cases"
    }

    object Features {
        const val SERIES = ":feature:series"
        const val DRIVER = ":feature:driver"
        const val TEAM = ":feature:team"
        const val VENUE = ":feature:venue"
    }
}