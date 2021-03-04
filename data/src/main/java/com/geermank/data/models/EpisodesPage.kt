package com.geermank.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EpisodesPage(
    @PrimaryKey val episodeId: Int,
    val page: Int,
    val totalPages: Int
)