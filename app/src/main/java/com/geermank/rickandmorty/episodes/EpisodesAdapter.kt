package com.geermank.rickandmorty.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geermank.common.recyclerview.OnListItemClickListener
import com.geermank.common.recyclerview.ClickableAdapter
import com.geermank.common.recyclerview.ClickableViewHolder
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.databinding.EpisodeListItemBinding

class EpisodesAdapter(
    episodes: List<EpisodeViewData>,
    clickListener: OnListItemClickListener<EpisodeViewData>
) : ClickableAdapter<EpisodeViewData, EpisodesViewHolder>(episodes, clickListener) {

    override fun createNewViewHolder(
        parent: ViewGroup,
        clickListener: OnListItemClickListener<EpisodeViewData>
    ): EpisodesViewHolder {
        return EpisodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.episode_list_item, parent, false),
            clickListener
        )
    }
}

class EpisodesViewHolder(
    view: View,
    listener: OnListItemClickListener<EpisodeViewData>
) : ClickableViewHolder<EpisodeViewData>(view, listener) {

    override fun bind(data: EpisodeViewData) {
        EpisodeListItemBinding.bind(itemView).apply {
            episodeName.text = data.title
            episodeAirDate.text = data.airDate
        }
    }
}
