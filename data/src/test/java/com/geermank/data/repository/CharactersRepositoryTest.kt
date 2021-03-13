package com.geermank.data.repository

import com.geermank.data.api.ApiUrlEntityIdExtractor
import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.cache.characters.CharactersCache
import com.geermank.data.models.CharacterDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class CharactersRepositoryTest {

    private val api = mock(RickAndMortyApi::class.java)
    private val cache = mock(CharactersCache::class.java)
    private val idExtractor = mock(ApiUrlEntityIdExtractor::class.java)

    private val repository = CharactersRepository(api, cache, idExtractor)

    private val charactersIds = listOf<Long>(1000, 1001, 1002)

    @Before
    fun setUp() {
        `when`(idExtractor.extractEntityId(com.geermank.data.any())).thenReturn(charactersIds)
    }

    @Test
    fun `getting characters when cached data is valid returns cached data`() = runBlockingTest {
        mockValidCache()

        val characters = repository.getCharactersByUrls(listOf("1", "2", "3"))

        verifyNoInteractions(api)
        assertCharacters(characters)
    }

    @Test
    fun `getting characters when cached data is invalid fetches them from api and saves them locally`() = runBlockingTest {
        mockInvalidCache()
        mockApiResponse()

        val characters = repository.getCharactersByUrls(listOf("1", "2", "3"))

        verify(cache).save(com.geermank.data.any())
        assertCharacters(characters)
    }

    private suspend fun mockValidCache() {
        val characters = mockCharacters()
        `when`(cache.getCharactersById(charactersIds)).thenReturn(characters)
    }

    private suspend fun mockInvalidCache() {
        `when`(cache.getCharactersById(charactersIds))
                .thenReturn(null)
                .thenReturn(mockCharacters())
    }

    private suspend fun mockApiResponse() {
        val characters = mockCharacters()
        `when`(api.getCharacters(charactersIds)).thenReturn(characters)
    }

    private fun mockCharacters(): List<CharacterDto> {
        val characterData1 = mock(CharacterDto::class.java).also { `when`(it.id).thenReturn(charactersIds[0]) }
        val characterData2 = mock(CharacterDto::class.java).also { `when`(it.id).thenReturn(charactersIds[1]) }
        val characterData3 = mock(CharacterDto::class.java).also { `when`(it.id).thenReturn(charactersIds[2]) }
        return listOf(characterData1, characterData2, characterData3)
    }

    private fun assertCharacters(characters: List<CharacterDto>) {
        characters.forEachIndexed { index, value ->
            assertEquals(charactersIds[index], value.id)
        }
    }
}
