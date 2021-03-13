package com.geermank.data.cache.episodes

import com.geermank.common.date.DateIntervals
import com.geermank.common.date.DateManager
import com.geermank.data.models.EpisodeDto
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class EpisodesCacheValidatorTest {

    private val cacheValidator = EpisodesCacheValidator(DateManager())

    @Test
    fun `getting sync interval returns a day interval`() {
        assertEquals(DateIntervals.INTERVAL_DAY, cacheValidator.getSyncIntervalTime())
    }

    @Test
    fun `getting last sync date returns the oldest episode sync date`() {
        val lastSyncDate = 13183789273217389
        val episode = mock(EpisodeDto::class.java).also { `when`(it.syncDate).thenReturn(lastSyncDate) }
        assertEquals(lastSyncDate, cacheValidator.getLastSyncDate(episode))
    }


}