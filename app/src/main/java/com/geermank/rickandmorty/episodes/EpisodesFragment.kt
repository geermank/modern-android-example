package com.geermank.rickandmorty.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geermank.common.BaseFragment
import com.geermank.rickandmorty.databinding.FragmentEpisodesBinding

const val EPISODES_FRAGMENT_TAG = "EPISODES_FRAGMENT_TAG"

class EpisodesFragment : BaseFragment() {

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
}
