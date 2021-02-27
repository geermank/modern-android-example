package com.geermank.rickandmorty.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.geermank.common.BaseViewModel
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository

class EpisodesViewModel @ViewModelInject constructor(
    private val repository: EpisodesRepository
) : BaseViewModel() {

    val episodes = MutableLiveData<List<EpisodeViewData>>()

    init {
        runCoroutine {
            val episodesData = repository.getEpisodes()
            episodes.value = convertToViewDataModel(episodesData)
        }
    }

    override fun onCoroutineError(error: Throwable) {
        // TODO handle error
    }

    private fun convertToViewDataModel(episodesData: List<EpisodeDto>): List<EpisodeViewData> {
        return episodesData.map {
            EpisodeViewData(
                it.id,
                "${it.episode} - ${it.name}",
                it.airDate
            )
        }
    }
}
