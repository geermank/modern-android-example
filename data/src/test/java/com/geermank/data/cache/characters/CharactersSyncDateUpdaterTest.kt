package com.geermank.data.cache.characters

import com.geermank.common.date.DateManager
import com.geermank.data.models.CharacterDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class CharactersSyncDateUpdaterTest {

    @Test
    fun `telling updater to update last date value executes the command in the local database`() = runBlockingTest {
        val dateManager = DateManager()
        val charactersDao = mock(CharactersDao::class.java)
        val syncDateUpdater = CharactersSyncDateUpdater(dateManager, charactersDao)

        syncDateUpdater.updateSyncDate(getCharactersList())

        verify(charactersDao).updateSyncDate(anyLong(), anyList())
    }

    private fun getCharactersList(): List<CharacterDto> {
        val character = mock(CharacterDto::class.java)
        return listOf(character)
    }
}
