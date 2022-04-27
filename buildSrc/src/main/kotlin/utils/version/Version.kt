package utils.version

import utils.version.VersionType.*
import kotlin.text.RegexOption.IGNORE_CASE

private val stableVersionRegex = """(?:\d+\.)*\d+""".toRegex()
private val rcSuffix = """rc|m\d+""".toRegex(IGNORE_CASE)

val String.type: VersionType
    get() = when {
        matches(stableVersionRegex) -> STABLE
        contains("alpha", ignoreCase = true) -> ALPHA
        contains("beta", ignoreCase = true) -> BETA
        contains(rcSuffix) -> RC
        else -> UNKNOWN
    }

val ignoredModules = setOf(
    "org.jacoco:org.jacoco.ant"
)