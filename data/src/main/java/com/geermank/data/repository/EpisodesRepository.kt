package com.geermank.data.repository

import com.geermank.data.models.EpisodeDto
import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.api.models.ResponseDto
import com.geermank.data.repository.paging.EpisodesDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun getEpisodes(page: Int): ResponseDto<EpisodeDto> {
        return rickAndMortyApi.getEpisodes(page)
    }
}
