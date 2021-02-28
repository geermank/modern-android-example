package com.geermank.rickandmorty.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.geermank.common.recyclerview.OnListItemClickListener
import com.geermank.common.recyclerview.ClickableAdapter
import com.geermank.common.recyclerview.ClickableViewHolder
import com.geermank.data.models.EpisodeDto
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.databinding.EpisodeListItemBinding

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EpisodeDto>() {
    override fun areItemsTheSame(oldItem: EpisodeDto, newItem: EpisodeDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeDto, newItem: EpisodeDto): Boolean {
        return oldItem == newItem
    }
}

class EpisodesAdapter(
    clickListener: OnListItemClickListener<EpisodeDto>
) : ClickableAdapter<EpisodeDto, EpisodesViewHolder>(clickListener, DIFF_CALLBACK) {

    override fun createNewViewHolder(
        parent: ViewGroup,
        clickListener: OnListItemClickListener<EpisodeDto>
    ): EpisodesViewHolder {
        return EpisodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.episode_list_item, parent, false),
            clickListener
        )
    }
}

class EpisodesViewHolder(
    view: View,
    listener: OnListItemClickListener<EpisodeDto>
) : ClickableViewHolder<EpisodeDto>(view, listener) {

    override fun bind(data: EpisodeDto) {
        EpisodeListItemBinding.bind(itemView).apply {
            episodeName.text = "${data.episode} - ${data.name}"
            episodeAirDate.text = data.airDate
        }
    }
}
