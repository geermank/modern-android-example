package com.geermank.data.cache.episodes

import com.geermank.data.cache.InvalidateCacheStrategy

class DeleteAllEpisodesStrategy : InvalidateCacheStrategy<EpisodesDao> {

    override suspend fun invalidateCache(dao: EpisodesDao) {
        dao.deleteAllEpisodes()
        dao.deleteAllEpisodesPaging()
    }
}
