package com.geermank.data.cache.characters

import com.geermank.common.date.DateIntervals
import com.geermank.common.date.DateManager
import com.geermank.data.cache.CacheStateValidator
import com.geermank.data.models.CharacterDto
import javax.inject.Inject

class CharactersCacheValidator @Inject constructor(
    dateManager: DateManager
) : CacheStateValidator<CharacterDto>(dateManager) {

    override fun getSyncIntervalTime(): Long {
        return DateIntervals.INTERVAL_DAY
    }

    override fun getLastSyncDate(oldestRegister: CharacterDto): Long {
        return oldestRegister.syncDate
    }
}
