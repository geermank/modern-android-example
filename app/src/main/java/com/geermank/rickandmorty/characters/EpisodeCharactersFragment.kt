package com.geermank.rickandmorty.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.geermank.common.presentation.fragments.BaseFragment
import com.geermank.data.models.CharacterDto
import com.geermank.data.models.EpisodeDto
import com.geermank.rickandmorty.databinding.FragmentEpisodeCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeCharactersFragment : BaseFragment() {
    
    companion object {
        const val TAG = "EpisodeCharactersFragment"

        fun newInstance(episode: EpisodeDto) = EpisodeCharactersFragment().apply {
            arguments = Bundle().also { it.putParcelable(EPISODE_ARGUMENT, episode) }
        }
    }

    private lateinit var binding: FragmentEpisodeCharactersBinding
    private lateinit var adapter: EpisodeCharactersAdapter

    private val viewModel: EpisodeCharactersViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEpisodeCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characters.observe(viewLifecycleOwner) {
            setUpCharactersList(it)
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpCharactersList(characters: List<CharacterDto>) {
        binding.rvCharacters.adapter = EpisodeCharactersAdapter(characters)
    }
}