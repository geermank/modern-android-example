package com.geermank.data.api

import com.geermank.data.api.models.PaginatedResponseDto
import com.geermank.data.models.CharacterDto
import com.geermank.data.models.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): PaginatedResponseDto<EpisodeDto>

    @GET("character/{ids}")
    suspend fun getCharactersById(@Path("ids") concatenatedIds: String): List<CharacterDto>
}
