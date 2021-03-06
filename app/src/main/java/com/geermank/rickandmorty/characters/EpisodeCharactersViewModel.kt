package com.geermank.rickandmorty.characters

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.geermank.common.presentation.viewmodel.BaseViewModel
import com.geermank.data.models.CharacterDto
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.CharactersRepository

const val EPISODE_ARGUMENT = "EPISODE_ARGUMENT"
private const val CHARACTERS_ARGUMENT = "CHARACTERS_ARGUMENT"

class EpisodeCharactersViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val characters = MutableLiveData<List<CharacterDto>>()
    val onError = MutableLiveData<String>()

    init {
        if (savedStateHandle.contains(CHARACTERS_ARGUMENT)) {
            notifyCharactersList(savedStateHandle[CHARACTERS_ARGUMENT]!!)
        } else {
            loadCharacters()
        }
    }

    override fun onCoroutineError(error: Throwable) {
        onError.postValue(error.message)
    }

    private fun loadCharacters() {
        coroutineExecutor.runCoroutine {
            val episode: EpisodeDto = savedStateHandle[EPISODE_ARGUMENT]!!
            repository.getCharactersByUrls(episode.characters).run {
                saveCharactersForInstanceBundle(this)
                notifyCharactersList(this)
            }
        }
    }

    private fun notifyCharactersList(characters: List<CharacterDto>) {
        this.characters.postValue(characters)
    }

    private fun saveCharactersForInstanceBundle(characters: List<CharacterDto>) {
        savedStateHandle.set(CHARACTERS_ARGUMENT, characters)
    }
}
