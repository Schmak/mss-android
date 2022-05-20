package com.mss.core.utils

fun interface Mapper<A, B> : (A) -> B {
    override fun invoke(obj: A) = obj.map()

    fun A.map(): B
}