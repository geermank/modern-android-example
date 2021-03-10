package com.geermank.data

import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.api.models.PaginatedResponseInfoDto
import com.geermank.data.cache.episodes.EpisodesCache
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class EpisodesRepositoryTest {

    private val api = mock(RickAndMortyApi::class.java)
    private val cache = mock(EpisodesCache::class.java)

    private val repository = EpisodesRepository(api, cache)

    @Test
    fun `getting episodes when there is valid data in cache returns cached data`() = runBlockingTest {
        mockValidDataInCache()

        val paginatedResponse = repository.getEpisodes(0)

        verifyNoInteractions(api)
        assertPaginatedResponse(paginatedResponse)
    }

    @Test
    fun `getting episodes when cache is invalid fetches new data`() = runBlockingTest {
        mockInvalidDataInCache()
        mockApiResponse()

        val paginatedResponse = repository.getEpisodes(0)

        assertPaginatedResponse(paginatedResponse)
    }

    private fun assertPaginatedResponse(paginatedResponse: PaginatedResponseDto<EpisodeDto>) {
        assertEquals(1, paginatedResponse.results.size)
        assertEquals(1001, paginatedResponse.results[0].id)
    }

    private suspend fun mockValidDataInCache() {
        val paginatedResponse = mockPaginatedResponse()
        `when`(cache.getEpisodesForPage(anyInt())).thenReturn(paginatedResponse)
    }

    private suspend fun mockInvalidDataInCache() {
        `when`(cache.getEpisodesForPage(anyInt()))
            .thenReturn(null)
            .thenReturn(mockPaginatedResponse())
    }

    private fun mockPaginatedResponse(): PaginatedResponseDto<EpisodeDto> {
        val episodeData = mock(EpisodeDto::class.java).also { `when`(it.id).thenReturn(1001) }
        val episodes = listOf(episodeData)
        return PaginatedResponseDto(PaginatedResponseInfoDto(10), episodes)
    }

    private suspend fun mockApiResponse() {
        val paginatedResponse = mockPaginatedResponse()
        `when`(api.getEpisodes(anyInt())).thenReturn(paginatedResponse)
    }
}
