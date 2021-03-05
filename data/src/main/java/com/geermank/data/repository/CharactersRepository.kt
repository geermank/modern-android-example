package com.geermank.data.repository

import com.geermank.data.api.CharacterUrlProcessor
import com.geermank.data.api.RickAndMortyApi
import com.geermank.data.cache.characters.CharactersCache
import com.geermank.data.models.CharacterDto
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val charactersCache: CharactersCache,
    private val characterUrlProcessor: CharacterUrlProcessor
) : Repository() {

    suspend fun getCharactersByUrls(urls: List<String>): List<CharacterDto> {
        val charactersIds = characterUrlProcessor.process(urls)
        val cachedCharacters = charactersCache.getCharactersById(charactersIds)
        return if (cachedCharacters == null) {
            charactersCache.save(rickAndMortyApi.getCharacters(charactersIds))
            charactersCache.getCharactersById(charactersIds)!!
        } else {
            cachedCharacters
        }
    }
}
