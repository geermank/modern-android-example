package com.geermank.data.cache

interface CacheStateValidator<T> {
    fun validCachedData(oldestRegister: T?): Boolean
}