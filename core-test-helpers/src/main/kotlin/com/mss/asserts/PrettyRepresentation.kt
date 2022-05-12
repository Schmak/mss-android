package com.mss.asserts

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.presentation.Representation
import java.lang.System.identityHashCode

@Suppress("ClassOrdering")
internal object PrettyRepresentation : Representation {
    private val prettyJsonMapper: ObjectMapper by lazy {
        jacksonObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(KotlinModule.Builder().build())
    }

    override fun toStringOf(obj: Any?): String =
        if (obj is String)
            "String:'\n$obj\n'"
        else
            obj.toStringOrNull { "$className $json" }

    override fun unambiguousToStringOf(obj: Any?): String =
        if (obj is String)
            "String(0x$address)'\n$obj\n'"
        else
            obj.toStringOrNull { "$className(0x$address) $json" }

    private val Any.className
        get() = this::class.simpleName

    private val Any.address
        get() = identityHashCode(this).toString(radix = 16)

    private val Any.json
        get() = prettyJsonMapper.writeValueAsString(this)

    private inline fun Any?.toStringOrNull(template: Any.() -> String) =
        this?.template() ?: "null"
}