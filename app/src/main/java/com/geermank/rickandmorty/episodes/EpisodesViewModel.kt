package com.geermank.rickandmorty.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.geermank.common.presentation.viewmodel.BaseViewModel
import com.geermank.common.extensions.asLiveData
import com.geermank.common.presentation.error.ErrorModel
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository
import com.geermank.data.repository.paging.EpisodesDataSourceFactory
import com.geermank.rickandmorty.errors.DataErrorMapper

const val EPISODES_PER_PAGE = 41
const val EPISODES_PREFETCH_SIZE = 20

class EpisodesViewModel @ViewModelInject constructor(repository: EpisodesRepository) : BaseViewModel() {

    val episodes: LiveData<PagedList<EpisodeDto>>
    val error = MutableLiveData<ErrorModel>()

    private val dataErrorMapper = DataErrorMapper(repository)

    init {
        val dataSourceFactory = EpisodesDataSourceFactory(repository, coroutineExecutor)
        episodes = dataSourceFactory.asLiveData(EPISODES_PER_PAGE, EPISODES_PREFETCH_SIZE)
    }

    override fun onCoroutineError(error: Throwable) {
        this.error.value = dataErrorMapper.getErrorModel(error)
    }
}
