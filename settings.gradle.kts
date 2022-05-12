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
    ":core-network", ":core-network-model", ":core-network-test-helpers",
    ":core-test-helpers", ":core-ui", ":core-utils",
)