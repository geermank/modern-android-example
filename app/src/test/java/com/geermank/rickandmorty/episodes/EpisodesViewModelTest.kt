package com.geermank.rickandmorty.episodes

import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.api.models.PaginatedResponseInfoDto
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository
import com.geermank.rickandmorty.ViewModelTest
import com.geermank.rickandmorty.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class EpisodesViewModelTest : ViewModelTest() {

    private lateinit var repository: EpisodesRepository

    @Before
    fun setUp() {
        repository = mock(EpisodesRepository::class.java)
    }

    @Test
    fun `on viewModel init activates episodes paginated list live data`() = runBlockingTest {
        `when`(repository.getEpisodes(anyInt())).thenReturn(mockEpisodesListResponse())
        val viewModel = EpisodesViewModel(repository)

        viewModel.episodes.getOrAwaitValue {
            assertNotNull(viewModel.episodes.value)
        }
    }

    private fun mockEpisodesListResponse(): PaginatedResponseDto<EpisodeDto> {
        val episodes = listOf(mockEpisode())
        val responseInfo = PaginatedResponseInfoDto(1)
        return PaginatedResponseDto(responseInfo, episodes)
    }

    private fun mockEpisode() = EpisodeDto(
        1,
        "Pilot",
        "2015-02-19",
        "S01E01",
        listOf("Rick"),
        "url",
        "2013-02-19",
        10123123
    )

}