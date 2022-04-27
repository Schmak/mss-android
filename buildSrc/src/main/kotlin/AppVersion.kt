@file:Suppress("MissingPackageDeclaration")

object AppVersion {
    private const val major = 0
    private const val minor = 1
    private const val patch = 0

    const val code = major * 10_000 + minor * 100 + patch
    const val name = "$major.$minor.$patch"
}