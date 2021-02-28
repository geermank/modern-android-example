package com.geermank.data.api

import com.geermank.data.api.models.ResponseDto
import com.geermank.data.models.EpisodeDto
import javax.inject.Inject

private const val API_URL = "https://rickandmortyapi.com/api/"

class RickAndMortyApi @Inject constructor() : BaseApi() {

    private val service: RickAndMortyService = buildService()

    suspend fun getEpisodes(page: Int): ResponseDto<EpisodeDto> {
        return service.getEpisodes(page)
    }

    override fun getBaseUrl(): String {
        return API_URL
    }
}
