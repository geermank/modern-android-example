package com.geermank.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Episodes")
@Parcelize
data class EpisodeDto(
    @PrimaryKey val id: Int,
    val name: String,
    @field:Json(name = "air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    val syncDate: Long
) : Parcelable
