package com.geermank.data.cache

import com.geermank.common.date.DateIntervals
import com.geermank.common.date.DateManager

abstract class CacheStateValidator<T>(private val dateManager: DateManager) {

    fun validCachedData(oldestRegister: T?): Boolean {
        if (oldestRegister == null) {
            return false
        }
        return dateManager.compareDiffToNowWithInterval(getLastSyncDate(oldestRegister), getSyncIntervalTime()) > 0
    }

    @DateIntervals
    abstract fun getSyncIntervalTime(): Long

    abstract fun getLastSyncDate(oldestRegister: T): Long
}
