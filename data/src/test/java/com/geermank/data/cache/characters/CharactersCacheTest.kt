package com.geermank.data.cache.characters

import com.geermank.data.any
import com.geermank.data.models.CharacterDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharactersCacheTest {

    @Mock
    lateinit var charactersDao: CharactersDao
    @Mock
    lateinit var cacheValidator: CharactersCacheValidator
    @Mock
    lateinit var syncDateUpdater: CharactersSyncDateUpdater

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `getting characters when no data is cached returns null`() = runBlockingTest {
        mockNoCachedData()

        val cache = CharactersCache(charactersDao, cacheValidator, syncDateUpdater)
        val characters = cache.getCharactersById(listOf(1, 2, 4))

        assertNull(characters)
    }

    @Test
    fun `getting characters when cached data is expired, deletes all local data and returns null`() = runBlockingTest {
        mockExpiredCachedData()

        val cache = CharactersCache(charactersDao, cacheValidator, syncDateUpdater)
        val characters = cache.getCharactersById(listOf(1, 2, 4))

        assertNull(characters)
    }

    @Test
    fun `getting characters when there's no data for the given ids returns null`() = runBlockingTest {
        mockNoCharacterSavedLocally()

        val cache = CharactersCache(charactersDao, cacheValidator, syncDateUpdater)
        val characters = cache.getCharactersById(listOf(1, 2, 4))

        assertNull(characters)
    }

    @Test
    fun `getting characters when not all characters of the given ids are saved locally returns null`() = runBlockingTest {
        mockNotAllCharactersSavedLocally(10)

        val cache = CharactersCache(charactersDao, cacheValidator, syncDateUpdater)
        val characters = cache.getCharactersById(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

        assertNull(characters)
    }

    @Test
    fun `getting characters when all of the given ids are saved locally returns a list of them`() = runBlockingTest {
        mockAllCharactersSavedLocally(10)

        val cache = CharactersCache(charactersDao, cacheValidator, syncDateUpdater)
        val charactersIds = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val characters = cache.getCharactersById(charactersIds)

        assertNotNull(characters)
        assertEquals(charactersIds.size, characters!!.size)
    }


    private suspend fun mockNoCachedData() {
        `when`(charactersDao.getOldestCharacter()).thenReturn(null)
        `when`(cacheValidator.validCachedData(any())).thenReturn(false)
    }

    private suspend fun mockExpiredCachedData() {
        val character = mock(CharacterDto::class.java)
        `when`(charactersDao.getOldestCharacter()).thenReturn(character)
        `when`(cacheValidator.validCachedData(any())).thenReturn(false)
    }

    private suspend fun mockNoCharacterSavedLocally() {
        mockValidCachedData()
        `when`(charactersDao.getByIds(anyList())).thenReturn(emptyList())
    }

    private suspend fun mockNotAllCharactersSavedLocally(charactersIdsCount: Int) {
        mockValidCachedData()

        val characters = ArrayList<CharacterDto>()
        for (index in 0..(charactersIdsCount - 2)) {
            val characterData = mock(CharacterDto::class.java)
            characters.add(characterData)
        }
        `when`(charactersDao.getByIds(anyList())).thenReturn(characters)
    }

    private suspend fun mockAllCharactersSavedLocally(charactersIdsCount: Int) {
        mockValidCachedData()

        val characters = ArrayList<CharacterDto>()
        for (index in 0 until charactersIdsCount) {
            val characterData = mock(CharacterDto::class.java)
            characters.add(characterData)
        }
        `when`(charactersDao.getByIds(anyList())).thenReturn(characters)
    }

    private suspend fun mockValidCachedData() {
        val character = mock(CharacterDto::class.java)
        `when`(charactersDao.getOldestCharacter()).thenReturn(character)
        `when`(cacheValidator.validCachedData(any())).thenReturn(true)
    }
}
