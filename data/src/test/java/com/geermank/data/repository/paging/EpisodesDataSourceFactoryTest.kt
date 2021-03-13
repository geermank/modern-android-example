package com.geermank.data.repository.paging

import com.geermank.common.coroutines.CoroutineExecutor
import com.geermank.data.repository.EpisodesRepository
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class EpisodesDataSourceFactoryTest {

    @Test
    fun `creating a data source returns the correct instance`() {
        val repository = mock(EpisodesRepository::class.java)
        val coroutineExecutor = mock(CoroutineExecutor::class.java)

        val dataSource = EpisodesDataSourceFactory(repository, coroutineExecutor).create()

        assertTrue(dataSource is EpisodesPagingDataSource)
    }
}
