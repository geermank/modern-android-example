package com.geermank.rickandmorty

import android.os.Bundle
import com.geermank.common.presentation.fragments.FragmentContainerActivity
import com.geermank.rickandmorty.databinding.ActivityMainBinding
import com.geermank.rickandmorty.episodes.EPISODES_FRAGMENT_TAG
import com.geermank.rickandmorty.episodes.EpisodesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentContainerActivity() {

    private lateinit var binding: ActivityMainBinding

    override val fragmentContainerId: Int
        get() = binding.fragmentContainer.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInitialFragment(savedInstanceState != null)
    }

    private fun setInitialFragment(restoringFromPreviousInstance: Boolean) {
        if (restoringFromPreviousInstance) {
            return
        }
        addFragment(EpisodesFragment(), EPISODES_FRAGMENT_TAG)
    }
}
