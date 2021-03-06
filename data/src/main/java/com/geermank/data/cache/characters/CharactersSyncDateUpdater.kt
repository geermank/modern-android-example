package com.geermank.data.cache.characters

import com.geermank.common.date.DateManager
import com.geermank.data.models.CharacterDto
import javax.inject.Inject

class CharactersSyncDateUpdater @Inject constructor(
    private val dateManager: DateManager,
    private val charactersDao: CharactersDao
) {

    suspend fun updateSyncDate(characters: List<CharacterDto>) {
        charactersDao.updateSyncDate(dateManager.now(), characters.map { it.id })
    }
}
