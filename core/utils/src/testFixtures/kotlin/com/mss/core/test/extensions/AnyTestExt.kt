package com.mss.core.test.extensions

@Suppress("UNCHECKED_CAST")
fun <T> Any.getField(name: String): T =
    with(this.javaClass.getDeclaredField(name)) {
        isAccessible = true
        get(this@getField) as T
    }