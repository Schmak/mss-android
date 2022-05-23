@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "android-mss"
include(
    ":app",
    ":core:network:api:v3", ":core:network:api:v4",
    ":core:network:common", ":core:network:credentials",
    ":core:ui", ":core:utils",
    ":domain:model", ":domain:repositories", ":domain:use-cases",
    ":feature:driver", ":feature:series", ":feature:results", ":feature:team", ":feature:venue",
)