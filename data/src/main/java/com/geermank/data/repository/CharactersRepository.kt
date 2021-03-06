package com.geermank.data.repository

import com.geermank.data.api.ApiUrlEntityIdExtractor
import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.cache.characters.CharactersCache
import com.geermank.data.models.CharacterDto
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val charactersCache: CharactersCache,
    private val characterUrlProcessor: ApiUrlEntityIdExtractor
) : Repository() {

    suspend fun getCharactersByUrls(urls: List<String>): List<CharacterDto> {
        val charactersIds = characterUrlProcessor.extractEntityId(urls)
        val cachedCharacters = charactersCache.getCharactersById(charactersIds)
        return if (cachedCharacters == null) {
            val syncedCharacters = rickAndMortyApi.getCharacters(charactersIds)
            charactersCache.save(syncedCharacters)
            charactersCache.getCharactersById(charactersIds)!!
        } else {
            cachedCharacters
        }
    }
}
