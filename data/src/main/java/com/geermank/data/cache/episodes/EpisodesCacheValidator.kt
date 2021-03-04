package com.geermank.data.cache.episodes

import com.geermank.common.date.DateIntervals
import com.geermank.common.date.DateManager
import com.geermank.data.cache.CacheStateValidator
import com.geermank.data.models.EpisodeDto
import javax.inject.Inject

private const val TIME_REQUIRED_FOR_ANOTHER_SYNC = DateIntervals.INTERVAL_DAY

class EpisodesCacheValidator @Inject constructor(
    private val dateManager: DateManager
) : CacheStateValidator<EpisodeDto> {

    override fun validCachedData(oldestRegister: EpisodeDto?): Boolean {
        if (oldestRegister == null) {
            return true
        }
        return cacheRecentlyUpdated(oldestRegister.syncDate)
    }

    private fun cacheRecentlyUpdated(syncDate: Long): Boolean {
        return dateManager.compareDiffToNowWithInterval(syncDate, TIME_REQUIRED_FOR_ANOTHER_SYNC) > 0
    }
}
