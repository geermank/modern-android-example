package com.geermank.rickandmorty.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import com.geermank.common.presentation.fragments.BaseFragment
import com.geermank.common.recyclerview.OnListItemClickListener
import com.geermank.common.extensions.remove
import com.geermank.common.presentation.fragments.modal.ModalData
import com.geermank.common.presentation.fragments.modal.actions.FinishActivityAction
import com.geermank.data.models.EpisodeDto
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

const val EPISODES_FRAGMENT_TAG = "EPISODES_FRAGMENT_TAG"

@AndroidEntryPoint
class EpisodesFragment : BaseFragment(), OnListItemClickListener<EpisodeDto> {

    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var episodesAdapter: EpisodesAdapter

    private val viewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createListAdapter()
        observeEpisodes()
        observeErrors()
    }

    private fun createListAdapter() {
        episodesAdapter = EpisodesAdapter(this).also { binding.episodesList.adapter = it }
    }

    private fun observeEpisodes() {
        viewModel.episodes.observe(viewLifecycleOwner) {
            removeLoading()
            setUpEpisodesList(it)
        }
    }

    private fun observeErrors() {
        viewModel.error.observe(viewLifecycleOwner) {
            // TODO add retry option based on if the error is recoverable
            val modalData = ModalData(R.drawable.ic_error_outlined, it.title, it.cause, FinishActivityAction())
            showModal(modalData)
        }
    }

    private fun removeLoading() {
        binding.episodesLoading.remove()
    }

    private fun setUpEpisodesList(episodes: PagedList<EpisodeDto>) {
        episodesAdapter.submitList(episodes)
    }

    override fun onItemClick(item: EpisodeDto) {
        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
    }
}
