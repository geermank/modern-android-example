package com.geermank.data.cache.characters

import com.geermank.data.cache.InvalidateCacheStrategy

class DeleteAllCharactersStrategy : InvalidateCacheStrategy<CharactersDao> {

    override suspend fun invalidateCache(dao: CharactersDao) {
        dao.deleteAll()
    }
}
