package com.geermank.common.extensions

fun Long.positive(): Long {
    return if (this > 0) {
        this
    } else {
        this * (-1)
    }
}