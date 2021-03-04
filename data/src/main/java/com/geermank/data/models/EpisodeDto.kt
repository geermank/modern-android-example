package com.geermank.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Episodes")
@Parcelize
data class EpisodeDto(
    @PrimaryKey var id: Int,
    var name: String,
    @field:Json(name = "air_date")
    var airDate: String,
    var episode: String,
    var characters: List<String>,
    var url: String,
    var created: String,
    var syncDate: Long
) : Parcelable
