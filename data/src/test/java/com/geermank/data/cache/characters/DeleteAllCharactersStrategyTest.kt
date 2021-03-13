package com.geermank.data.cache.characters

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DeleteAllCharactersStrategyTest {

    @Test
    fun `given a characters dao, when calling invalidate delete all characters data`() = runBlockingTest {
        val charactersDao = mock(CharactersDao::class.java)
        val strategy = DeleteAllCharactersStrategy()

        strategy.invalidateCache(charactersDao)

        verify(charactersDao).deleteAll()
    }
}
