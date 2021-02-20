package com.geermank.data.api

import com.geermank.data.api.models.ResponseDto
import com.geermank.data.models.EpisodeDto
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("episode")
    suspend fun getAllEpisodes(): ResponseDto<EpisodeDto>
}
