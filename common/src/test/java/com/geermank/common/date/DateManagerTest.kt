package com.geermank.common.date

import com.geermank.common.date.DateIntervals.Companion.INTERVAL_DAY
import com.geermank.common.date.DateIntervals.Companion.INTERVAL_HOUR
import org.junit.Assert.assertEquals
import org.junit.Test

class DateManagerTest {

    private val dateManager = DateManager()

    @Test
    fun `interval day returns the correct amount of milliseconds`() {
        assertEquals(86400000, INTERVAL_DAY)
    }

    @Test
    fun `interval hour returns the correct amount of milliseconds`() {
        assertEquals(3600000, INTERVAL_HOUR)
    }

    @Test
    fun `now returns system current time in millis`() {
        assertEquals(System.currentTimeMillis(), dateManager.now())
    }

    @Test
    fun `diff to returns the difference between now and the given time in millis`() {
        val tomorrow = System.currentTimeMillis() + INTERVAL_DAY
        val difference = dateManager.diffToNow(tomorrow)
        assertEquals(INTERVAL_DAY, difference)
    }

    @Test
    fun `comparing diff to now with an equal interval returns zero`() {
        val tomorrow = System.currentTimeMillis() + INTERVAL_DAY
        dateManager.compareDiffToNowWithInterval(tomorrow, INTERVAL_DAY)
    }

    @Test
    fun `comparing diff to now when interval is smaller returns a negative number`() {
        val someDateInTheFuture = System.currentTimeMillis() + (INTERVAL_DAY * 2)
        dateManager.compareDiffToNowWithInterval(someDateInTheFuture, INTERVAL_DAY)
    }

    @Test
    fun `comparing diff to now when interval is larger returns a positive number`() {
        val someStrangeDate: Long = 10
        dateManager.compareDiffToNowWithInterval(someStrangeDate, INTERVAL_DAY)
    }
}
