package com.geermank.data.repository.paging

import androidx.paging.PageKeyedDataSource
import com.geermank.common.coroutines.CoroutineExecutor
import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository

class EpisodesPagingDataSource(
    private val repository: EpisodesRepository,
    private val coroutineExecutor: CoroutineExecutor
) : PageKeyedDataSource<Int, EpisodeDto>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, EpisodeDto>
    ) {
        coroutineExecutor.runCoroutine {
            val episodesResponse = repository.getEpisodes(1)
            callback.onResult(episodesResponse.results, null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EpisodeDto>) {
        loadEpisodesAndNotify(params, callback, ::getPreviousPageIfAvailable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EpisodeDto>) {
        loadEpisodesAndNotify(params, callback, ::getNextPageIfAvailable)
    }

    private fun loadEpisodesAndNotify(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, EpisodeDto>,
        page: (params: LoadParams<Int>, episodesResponse: PaginatedResponseDto<EpisodeDto>) -> Int?
    ) {
        coroutineExecutor.runCoroutine {
            val episodesResponse = repository.getEpisodes(params.key)
            callback.onResult(episodesResponse.results, page(params, episodesResponse))
        }
    }

    private fun getPreviousPageIfAvailable(
        params: LoadParams<Int>,
        episodesResponse: PaginatedResponseDto<EpisodeDto>
    ): Int? {
        return if (params.key == 1) {
            return null
        } else {
            params.key - 1
        }
    }

    private fun getNextPageIfAvailable(
        params: LoadParams<Int>,
        episodesResponse: PaginatedResponseDto<EpisodeDto>
    ): Int? {
        return if (episodesResponse.info.pages == params.key) {
            return null
        } else {
            params.key + 1
        }
    }
}