package com.geermank.rickandmorty.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.geermank.common.BaseViewModel
import com.geermank.domain.episodes.Episode
import com.geermank.domain.episodes.GetEpisodesUseCase
import java.util.*

class EpisodesViewModel @ViewModelInject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase
) : BaseViewModel() {

    val episodesViewData = MutableLiveData<List<EpisodeViewData>>()

    private val episodes = LinkedList<Episode>()

    init {
        runCoroutine {
            episodes.addAll(getEpisodesUseCase.execute(Unit))
            val viewModels = convertEpisodesToViewModel()
            episodesViewData.value = viewModels
        }
    }

    override fun onCoroutineError(error: Throwable) {

    }

    private fun convertEpisodesToViewModel(): List<EpisodeViewData> {
        return episodes.map {
            EpisodeViewData(
                it.getId(),
                it.getName(),
                it.getAirDate()
            )
        }
    }
}
