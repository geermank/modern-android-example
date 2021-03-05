package com.geermank.data.api

import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.models.CharacterDto
import com.geermank.data.models.EpisodeDto
import javax.inject.Inject

private const val API_URL = "https://rickandmortyapi.com/api/"

class RickAndMortyApi @Inject constructor() : BaseApi() {

    private val service: RickAndMortyService = buildService()

    suspend fun getEpisodes(page: Int): PaginatedResponseDto<EpisodeDto> {
        return service.getEpisodes(page)
    }

    suspend fun getCharacters(ids: List<Long>): List<CharacterDto> {
        return service.getCharactersById(ids.joinToString(","))
    }

    override fun getBaseUrl(): String {
        return API_URL
    }
}
