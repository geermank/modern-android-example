package com.geermank.domain.episodes

import android.os.Parcelable
import com.geermank.data.models.EpisodeDto
import com.geermank.data.repository.EpisodesRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
class Episode(private val data: EpisodeDto) : Parcelable {

    fun getId() = data.id

    fun getName() = "${data.episode} - ${data.name}"

    fun getAirDate() = data.airDate
}
