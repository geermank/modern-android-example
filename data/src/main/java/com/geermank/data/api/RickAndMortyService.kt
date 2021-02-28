package com.geermank.data.api

import com.geermank.data.api.models.ResponseDto
import com.geermank.data.models.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): ResponseDto<EpisodeDto>
}
