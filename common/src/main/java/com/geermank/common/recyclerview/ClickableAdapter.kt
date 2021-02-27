package com.geermank.common.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ClickableAdapter<ItemType, VH : ClickableViewHolder<ItemType>>(
    private val data: List<ItemType>,
    private val clickListener: OnListItemClickListener<ItemType>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemData = data[position]
        holder.apply {
            setClickListener(itemData)
            bind(itemData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createNewViewHolder(parent, clickListener)
    }

    abstract fun createNewViewHolder(
        parent: ViewGroup,
        clickListener: OnListItemClickListener<ItemType>
    ): VH
}

abstract class ClickableViewHolder<ItemType>(
    view: View,
    protected val clickListener: OnListItemClickListener<ItemType>
) : RecyclerView.ViewHolder(view) {

    fun setClickListener(data: ItemType) {
        itemView.setOnClickListener { clickListener.onItemClick(data) }
    }

    abstract fun bind(data: ItemType)
}
