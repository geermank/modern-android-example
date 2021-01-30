package com.geermank.domain.episodes

import com.geermank.domain.UseCase
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor() : UseCase<Unit, List<Episode>> {

    override fun execute(parameter: Unit): List<Episode> {
        return listOf(Episode())
    }
}