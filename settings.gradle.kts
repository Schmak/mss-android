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
    ":core:network:api", ":core:network:credentials",
    ":core:network:model", ":core:ui", ":core:utils",
    ":domain:model", ":domain:repositories",
    ":feature:series", ":feature:team", ":feature:venue",
)