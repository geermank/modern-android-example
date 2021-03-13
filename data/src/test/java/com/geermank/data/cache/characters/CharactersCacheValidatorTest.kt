package com.geermank.data.cache.characters

import com.geermank.common.date.DateIntervals
import com.geermank.common.date.DateManager
import com.geermank.data.models.CharacterDto
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CharactersCacheValidatorTest {

    private val charactersCacheValidator = CharactersCacheValidator(DateManager())

    @Test
    fun `asking for sync interval returns the correct value`() {
        assertEquals(DateIntervals.INTERVAL_DAY, charactersCacheValidator.getSyncIntervalTime())
    }

    @Test
    fun `asking for last sync date returns older register sync date`() {
        val lastSyncDate = 987381734444
        val character = mock(CharacterDto::class.java).also { `when`(it.syncDate).thenReturn(lastSyncDate) }
        assertEquals(lastSyncDate, charactersCacheValidator.getLastSyncDate(character))
    }
}
