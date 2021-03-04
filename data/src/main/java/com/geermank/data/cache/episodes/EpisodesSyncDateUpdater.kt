package com.geermank.data.cache.episodes

import com.geermank.common.date.DateManager
import com.geermank.data.models.EpisodeDto
import javax.inject.Inject

class EpisodesSyncDateUpdater @Inject constructor(
    private val dateManager: DateManager,
    private val episodesDao: EpisodesDao
) {

    suspend fun updateSyncDate(episodes: List<EpisodeDto>) {
        episodesDao.updateSyncDate(dateManager.now(), episodes.map { it.id })
    }
}
