package com.geermank.data.repository.paging

import androidx.paging.DataSource
import com.geermank.common.coroutines.CoroutineExecutor
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository

class EpisodesDataSourceFactory(
    private val repository: EpisodesRepository,
    private val coroutineExecutor: CoroutineExecutor
) : DataSource.Factory<Int, EpisodeDto>() {

    override fun create(): DataSource<Int, EpisodeDto> {
        return EpisodesPagingDataSource(repository, coroutineExecutor)
    }
}
