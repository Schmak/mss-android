package utils.version

enum class VersionType : Comparable<VersionType> {
    ALPHA,
    BETA,
    RC,
    STABLE,
    UNKNOWN,
}