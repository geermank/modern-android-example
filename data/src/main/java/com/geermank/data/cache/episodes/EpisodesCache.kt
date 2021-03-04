package com.geermank.data.cache.episodes

import com.geermank.data.api.models.ResponseDto
import com.geermank.data.api.models.ResponseInfoDto
import com.geermank.data.cache.InvalidateCacheStrategy
import com.geermank.data.models.EpisodeDto
import com.geermank.data.models.EpisodesPage
import javax.inject.Inject

class EpisodesCache @Inject constructor(
    private val episodesDao: EpisodesDao,
    private val cacheStateValidator: EpisodesCacheValidator,
    private val episodesSyncDateUpdater: EpisodesSyncDateUpdater
) {

    /**
     * Returns a list of episodes for a specific page or null if cache is invalid
     */
    suspend fun getEpisodesForPage(page: Int): ResponseDto<EpisodeDto>? {
        val oldestSyncedEpisode = episodesDao.getOlderEpisode()
         if (!cacheStateValidator.validCachedData(oldestSyncedEpisode)) {
             // general data is invalid, no matter the page we are querying
             // invalidate all cache and refresh data
             invalidateCache(DeleteAllEpisodesStrategy())
            return null
        }
        val episodes = episodesDao.getPageEpisodes(page)
        if (episodes.isEmpty()) {
            // cache is valid but we haven't synced this page yet
            return null
        }
        val totalPages = episodesDao.getTotalPages()
        return ResponseDto(ResponseInfoDto(totalPages), episodes)
    }

    suspend fun save(page: Int, response: ResponseDto<EpisodeDto>) {
        episodesDao.apply {
            // insert new episodes and update with refresh date
            insertEpisodes(response.results)
            episodesSyncDateUpdater.updateSyncDate(response.results)

            insertEpisodesPaging(response.results.map { EpisodesPage(it.id, page, response.info.pages) })
        }
    }

    private suspend fun invalidateCache(invalidateCacheStrategy: InvalidateCacheStrategy<EpisodesDao>) {
        invalidateCacheStrategy.invalidateCache(episodesDao)
    }
}
