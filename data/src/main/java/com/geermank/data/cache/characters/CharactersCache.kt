package com.geermank.data.cache.characters

import com.geermank.common.extensions.thisOrNull
import com.geermank.data.models.CharacterDto
import javax.inject.Inject

class CharactersCache @Inject constructor(
    private val charactersDao: CharactersDao,
    private val cacheValidator: CharactersCacheValidator,
    private val syncDateUpdater: CharactersSyncDateUpdater
) {

    suspend fun getCharactersById(ids: List<Long>): List<CharacterDto>? {
        val oldestSyncedCharacter = charactersDao.getOldestCharacter()
        if (!cacheValidator.validCachedData(oldestSyncedCharacter)) {
            invalidateCache()
            return null
        }
        return charactersDao.getByIds(ids).thisOrNull {
            // to return local copy of characters we must have them all
            it.isNotEmpty() && it.size == ids.size
        }
    }

    suspend fun save(characters: List<CharacterDto>) {
        charactersDao.save(characters)
        syncDateUpdater.updateSyncDate(characters)
    }

    private suspend fun invalidateCache() {
        DeleteAllCharactersStrategy().invalidateCache(charactersDao)
    }
}
