package com.geermank.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Characters")
@Parcelize
data class CharacterDto(
    @PrimaryKey
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val episode: List<String>,
    val syncDate: Long
) : Parcelable