package com.geermank.data.cache.episodes

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class DeleteAllEpisodesStrategyTest {

    @Test
    fun `invalidating cache deletes all episodes and episodes paging`() = runBlockingTest {
        val dao = mock(EpisodesDao::class.java)

        val deleteAllEpisodesStrategy = DeleteAllEpisodesStrategy()
        deleteAllEpisodesStrategy.invalidateCache(dao)

        verify(dao).deleteAllEpisodes()
        verify(dao).deleteAllEpisodesPaging()
    }
}
