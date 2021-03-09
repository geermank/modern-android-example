package com.geermank.rickandmorty.characters

import androidx.lifecycle.SavedStateHandle
import com.geermank.data.models.CharacterDto
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.CharactersRepository
import com.geermank.rickandmorty.*
import com.geermank.rickandmorty.any
import com.geermank.rickandmorty.eq
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class EpisodeCharactersViewModelTest : ViewModelTest() {

    private lateinit var repository: CharactersRepository
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        repository = mock(CharactersRepository::class.java)
        savedStateHandle = mock(SavedStateHandle::class.java)
    }

    @Test
    fun `when viewModel init is called and no data is saved on SavedStateHandle data is loaded from repository`() = runBlockingTest {
        // Arrange
        mockDataOnSaveStateHandle(false)

        val characters = mockCharacters()
        `when`(repository.getCharactersByUrls(any())).thenReturn(characters)

        // Act
        val viewModel = EpisodeCharactersViewModel(repository, savedStateHandle)

        // Assert
        verify(savedStateHandle).set(eq("CHARACTERS_ARGUMENT"), anyList<CharacterDto>())
        viewModel.characters.getOrAwaitValue {
            assertPostedCharacters(viewModel.characters.value)
        }
    }

    @Test
    fun `when viewModel init is called and the SavedStateHandle has data, post live data with the characters saved on it`() = runBlockingTest {
        // Arrange
        mockDataOnSaveStateHandle(true)

        // Act
        val viewModel = EpisodeCharactersViewModel(repository, savedStateHandle)

        // Assert
        viewModel.characters.getOrAwaitValue {
            assertPostedCharacters(viewModel.characters.value)
        }
    }

    @Test
    fun `when view model loads data from repository and something fails activates error viewModel`() = runBlockingTest {
        // Arrange
        mockDataOnSaveStateHandle(false)

        val runtimeException = RuntimeException("Coudn't reach the host :(")
        `when`(repository.getCharactersByUrls(any())).thenThrow(runtimeException)

        // Acts
        val viewModel = EpisodeCharactersViewModel(repository, savedStateHandle)

        // Assert
        viewModel.onError.getOrAwaitValue {
            viewModel.onError.value.let {
                assertNotNull(this)
                assertFalse(it!!.isRecoverable)
                assertEquals(R.string.error_generic_title, it.title)
                assertEquals(R.string.error_generic_subtitle, it.cause)
            }
        }
    }

    private fun mockDataOnSaveStateHandle(hasData: Boolean) {
        // first mock the episode. We will always have an episode at this point
        val episode = mockEpisodeWithCharacters()
        `when`(savedStateHandle.get<EpisodeDto>(EPISODE_ARGUMENT)).thenReturn(episode)

        // then mock the characters data. Here we might not have any yet
        `when`(savedStateHandle.contains("CHARACTERS_ARGUMENT")).thenReturn(hasData)
        if (hasData) {
            val characters = mockCharacters()
            `when`(savedStateHandle.get<List<CharacterDto>>("CHARACTERS_ARGUMENT")).thenReturn(characters)
        }
    }

    private fun mockEpisodeWithCharacters(): EpisodeDto {
        val characters = listOf("Char1", "Char2", "Char3")
        return mock(EpisodeDto::class.java).also { `when`(it.characters).thenReturn(characters) }
    }

    private fun mockCharacters(): List<CharacterDto> {
        val characterDataMock = mock(CharacterDto::class.java).also { `when`(it.id).thenReturn(100) }
        return listOf(characterDataMock)
    }

    private fun assertPostedCharacters(characters: List<CharacterDto>?) {
        assertNotNull(characters)
        assertEquals(1, characters!!.size)
        assertEquals(100, characters[0].id)
    }
}