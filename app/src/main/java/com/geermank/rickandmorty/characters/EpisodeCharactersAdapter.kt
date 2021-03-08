package com.geermank.rickandmorty.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geermank.common.ImageLoader
import com.geermank.data.models.CharacterDto
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.databinding.CharactersListItemBinding

class EpisodeCharactersAdapter(private val characters: List<CharacterDto>) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.characters_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(character: CharacterDto) {
        CharactersListItemBinding.bind(itemView).apply {
            ImageLoader.loadImage(character.image, ivCharacterIcon)
            tvCharacterName.text = character.name
            tvCharacterSpecie.text = character.species
            tvCharacterStatus.text = "Status: ${character.status}"
        }
    }
}
