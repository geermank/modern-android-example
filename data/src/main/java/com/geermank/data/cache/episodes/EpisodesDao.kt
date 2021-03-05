package com.geermank.data.cache.episodes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geermank.data.models.EpisodeDto
import com.geermank.data.models.EpisodesPage
import javax.inject.Singleton

@Singleton
@Dao
interface EpisodesDao {

    //region EPISODES
    @Query("SELECT * FROM Episodes e JOIN EpisodesPage ep ON e.id = ep.episodeId WHERE ep.page = :page")
    suspend fun getPageEpisodes(page: Int): List<EpisodeDto>

    @Query("SELECT * FROM Episodes ep1 INNER JOIN (SELECT id, MIN(syncDate) FROM Episodes GROUP BY id) ep2 ON ep1.id = ep2.id")
    suspend fun getOldestEpisode(): EpisodeDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(results: List<EpisodeDto>)

    @Query("UPDATE Episodes SET syncDate = :date WHERE id IN (:episodesIds)")
    suspend fun updateSyncDate(date: Long, episodesIds: List<Int>)

    @Query("DELETE FROM Episodes")
    suspend fun deleteAllEpisodes()

    // endregion

    // region EPISODES PAGING
    @Query("SELECT MAX(totalPages) FROM EpisodesPage")
    suspend fun getTotalPages(): Int

    @Insert
    suspend fun insertEpisodesPaging(episodesPaging: List<EpisodesPage>)

    @Query("DELETE FROM EpisodesPage")
    suspend fun deleteAllEpisodesPaging()

    // endregion
}