package com.geermank.data.repository.paging

import androidx.paging.PageKeyedDataSource
import com.geermank.common.coroutines.CoroutineExecutor
import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.api.models.PaginatedResponseInfoDto
import com.geermank.data.eq
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class EpisodesPagingDataSourceTest {

    private val episodesRepository = mock(EpisodesRepository::class.java)
    private val coroutineExecutor = CoroutineExecutor(TestCoroutineScope(), CoroutineExceptionHandler { _, _, -> })

    private val episodesPagingDataSource = EpisodesPagingDataSource(episodesRepository, coroutineExecutor)

    @Mock
    lateinit var loadCallback: PageKeyedDataSource.LoadCallback<Int, EpisodeDto>

    @Mock
    lateinit var initialParams: PageKeyedDataSource.LoadInitialParams<Int>
    @Mock
    lateinit var initialCallback: PageKeyedDataSource.LoadInitialCallback<Int, EpisodeDto>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `loading initial data gets the first page and calls the callback`() = runBlockingTest {
        mockRepositoryResponse()
        episodesPagingDataSource.loadInitial(initialParams, initialCallback)
        verify(initialCallback).onResult(anyList(), eq(null), eq(2))
    }

    @Test
    fun `load before returns null as adjacentPageKey when the given page is the first`() = runBlockingTest {
        mockRepositoryResponse()
        val loadParams = createLoadParams(1)

        episodesPagingDataSource.loadBefore(loadParams, loadCallback)

        verify(loadCallback).onResult(anyList(), eq(null))
    }

    @Test
    fun `load before returns a lower key as adjacentPageKey when the given page isn't the first`() = runBlockingTest {
        mockRepositoryResponse()
        val loadParams = createLoadParams(10)

        episodesPagingDataSource.loadBefore(loadParams, loadCallback)

        verify(loadCallback).onResult(anyList(), eq(9))
    }

    @Test
    fun `load after returns null as adjacentPageKey when the given page is the last one`() = runBlockingTest {
        mockRepositoryResponse()
        val loadParams = createLoadParams(5)

        episodesPagingDataSource.loadAfter(loadParams, loadCallback)

        verify(loadCallback).onResult(anyList(), eq(null))
    }

    @Test
    fun `load after returns a higher key as adjacentPageKey when the given page isn't the last one`() = runBlockingTest {
        mockRepositoryResponse()
        val loadParams = createLoadParams(2)

        episodesPagingDataSource.loadAfter(loadParams, loadCallback)

        verify(loadCallback).onResult(anyList(), eq(3))
    }

    private fun createLoadParams(page: Int): PageKeyedDataSource.LoadParams<Int> {
        // coudn't create a mock of this class and access the page variable in the `when`
        // so I had to do it this way
        return PageKeyedDataSource.LoadParams<Int>(page, 1)
    }

    private suspend fun mockRepositoryResponse() {
        val episodes = listOf(mockEpisode())
        val responseInfo = PaginatedResponseInfoDto(5)
        val paginatedResponse = PaginatedResponseDto(responseInfo, episodes)
        `when`(episodesRepository.getEpisodes(anyInt())).thenReturn(paginatedResponse)
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
