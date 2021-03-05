package com.geermank.common.extensions

fun <T> T.thisOrNull(condition: (T) -> Boolean): T? {
    return if (condition(this)) {
        this
    } else {
        null
    }
}

fun <T> T.thisOrDefault(default: () -> T): T {
    return this ?: default()
}
