package com.geermank.data.cache.episodes

import com.geermank.common.date.DateManager
import com.geermank.data.capture
import com.geermank.data.eq
import com.geermank.data.models.EpisodeDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class EpisodesSyncDateUpdaterTest {

    @Captor
    private lateinit var episodesIdsCaptor: ArgumentCaptor<List<Int>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `given a list of episodes, updates their sync date locally`() = runBlockingTest {
        val episodes = mockEpisodes()

        val dao = mock(EpisodesDao::class.java)
        val dateManager = mock(DateManager::class.java).also { `when`(it.now()).thenReturn(12312312132) }
        val episodesSyncDateUpdater = EpisodesSyncDateUpdater(dateManager, dao)

        episodesSyncDateUpdater.updateSyncDate(episodes)

        verify(dao).updateSyncDate(eq(12312312132), capture(episodesIdsCaptor))

        val episodesIds = episodesIdsCaptor.value
        assertEquals(3, episodesIds.size)
        assertEquals(1, episodesIds[0])
        assertEquals(2, episodesIds[1])
        assertEquals(3, episodesIds[2])
    }

    private fun mockEpisodes() = ArrayList<EpisodeDto>().apply {
        mock(EpisodeDto::class.java).also {
            `when`(it.id).thenReturn(1)
            add(it)
        }
        mock(EpisodeDto::class.java).also {
            `when`(it.id).thenReturn(2)
            add(it)
        }
        mock(EpisodeDto::class.java).also {
            `when`(it.id).thenReturn(3)
            add(it)
        }
    }
}