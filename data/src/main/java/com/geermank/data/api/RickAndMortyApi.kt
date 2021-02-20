package com.geermank.data.api

import javax.inject.Inject

class RickAndMortyApi @Inject constructor() : BaseApi() {

    private val service: RickAndMortyService = buildService()

    suspend fun getAllEpisodes() = service.getAllEpisodes()

    override fun getBaseUrl(): String {
        return "https://rickandmortyapi.com/api/"
    }
}