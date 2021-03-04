package com.geermank.data.repository

import com.geermank.data.models.EpisodeDto
import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.api.models.ResponseDto
import com.geermank.data.cache.episodes.EpisodesCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val episodesCache: EpisodesCache
) : Repository() {

    suspend fun getEpisodes(page: Int): ResponseDto<EpisodeDto> {
        val cachedEpisodes = episodesCache.getEpisodesForPage(page)
        if (cachedEpisodes == null) {
            val response = rickAndMortyApi.getEpisodes(page)
            episodesCache.save(page, response)
        }
        return episodesCache.getEpisodesForPage(page)!!
    }
}
