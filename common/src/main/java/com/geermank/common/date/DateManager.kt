package com.geermank.common.date

import com.geermank.common.extensions.positive
import java.util.*
import javax.inject.Inject

class DateManager @Inject constructor() {

    private val calendar = Calendar.getInstance().also { it.timeInMillis = System.currentTimeMillis() }

    fun now(): Long {
        calendar.timeInMillis = System.currentTimeMillis()
        return calendar.timeInMillis
    }

    fun diffToNow(otherDateInMillis: Long): Long {
        return (now() - otherDateInMillis).positive()
    }

    /**
     * @return 0 if are equals, a positive number if interval is bigger, a negative number if interval is smaller
     */
    fun compareDiffToNowWithInterval(time: Long, @DateIntervals interval: Long): Int {
        return interval.compareTo(diffToNow(time))
    }
}