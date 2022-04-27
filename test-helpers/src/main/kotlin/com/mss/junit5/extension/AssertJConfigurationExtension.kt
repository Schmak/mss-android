package com.mss.junit5.extension

import com.mss.asserts.PrettyRepresentation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Junit5 extension that registers custom AssertJ representation if
 * environment variable is set
 */
internal class AssertJConfigurationExtension : BeforeAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        if (System.getenv(PROPERTY).equals(PRETTY, ignoreCase = true))
            Assertions.useRepresentation(PrettyRepresentation)
    }

    companion object {
        private const val PROPERTY = "org.assertj.representation"
        private const val PRETTY = "pretty"
    }
}