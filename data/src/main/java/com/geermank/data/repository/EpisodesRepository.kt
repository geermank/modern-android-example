package com.geermank.data.repository

import com.geermank.data.models.EpisodeDto
import com.geermank.data.api.RickAndMortyApi
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun getEpisodes(): List<EpisodeDto> {
        val response = rickAndMortyApi.getAllEpisodes()
        return response.results
    }
}
