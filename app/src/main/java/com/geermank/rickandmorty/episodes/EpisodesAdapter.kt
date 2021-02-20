package com.geermank.rickandmorty.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geermank.common.OnListItemClickListener
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.databinding.EpisodeListItemBinding

class EpisodesAdapter(private val episodes: List<EpisodeViewData>) : RecyclerView.Adapter<EpisodesViewHolder>() {

    private var itemClickListener: OnListItemClickListener<EpisodeViewData>? = null

    fun setOnItemClickListener(itemClickListener: OnListItemClickListener<EpisodeViewData>) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.episode_list_item, parent, false),
            itemClickListener
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}

class EpisodesViewHolder(
    view: View,
    private val listener: OnListItemClickListener<EpisodeViewData>?
) : RecyclerView.ViewHolder(view) {

    fun bind(viewData: EpisodeViewData) {
        EpisodeListItemBinding.bind(itemView).apply {
            episodeName.text = viewData.nameLabelValue
            episodeAirDate.text = viewData.airDateLabelValue
            root.setOnClickListener { listener?.onItemClick(viewData) }
        }
    }
}
