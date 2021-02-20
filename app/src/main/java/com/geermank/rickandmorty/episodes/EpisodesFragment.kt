package com.geermank.rickandmorty.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.geermank.common.BaseFragment
import com.geermank.common.OnListItemClickListener
import com.geermank.rickandmorty.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

const val EPISODES_FRAGMENT_TAG = "EPISODES_FRAGMENT_TAG"

@AndroidEntryPoint
class EpisodesFragment : BaseFragment(), OnListItemClickListener<EpisodeViewData> {

    private lateinit var binding: FragmentEpisodesBinding

    private val viewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.episodesViewData.observe(viewLifecycleOwner) {
            setUpEpisodesList(it)
        }
    }

    private fun setUpEpisodesList(episodes: List<EpisodeViewData>) {
        binding.episodesList.adapter = EpisodesAdapter(episodes).also { it.setOnItemClickListener(this) }
    }

    override fun onItemClick(item: EpisodeViewData) {
        Toast.makeText(context, item.nameLabelValue, Toast.LENGTH_SHORT).show()
    }
}
