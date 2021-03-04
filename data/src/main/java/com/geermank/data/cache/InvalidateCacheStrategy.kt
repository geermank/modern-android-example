package com.geermank.data.cache

interface InvalidateCacheStrategy<DaoType> {
    suspend fun invalidateCache(dao: DaoType)
}
