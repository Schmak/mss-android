package com.mss.extensions

val <K, V> Map<K, V>?.nullIfEmpty: Map<K, V>?
    get() = this?.ifEmpty { null }