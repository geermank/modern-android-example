package com.geermank.domain.extensions

import com.geermank.data.models.EpisodeDto
import com.geermank.domain.episodes.Episode

fun List<EpisodeDto>.toDomainModel(): List<Episode> {
    return map { Episode(it) }
}