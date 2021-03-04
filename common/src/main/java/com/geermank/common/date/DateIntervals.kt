package com.geermank.common.date

import androidx.annotation.LongDef

@LongDef(DateIntervals.INTERVAL_MINUTE,
    DateIntervals.INTERVAL_HOUR,
    DateIntervals.INTERVAL_DAY)
annotation class DateIntervals {
    companion object {
        const val INTERVAL_MINUTE: Long = 1000 * 60
        const val INTERVAL_HOUR: Long = INTERVAL_MINUTE * 60
        const val INTERVAL_DAY: Long = INTERVAL_HOUR * 24
    }
}
