package com.geermank.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeDto(
    val id: Int,
    val name: String,
    @field:Json(name = "air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Parcelable