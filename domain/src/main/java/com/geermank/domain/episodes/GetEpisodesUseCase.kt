package com.geermank.domain.episodes

import com.geermank.data.repository.EpisodesRepository
import com.geermank.domain.UseCase
import com.geermank.domain.extensions.toDomainModel
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) : UseCase<Unit, List<Episode>> {

    override suspend fun execute(parameter: Unit): List<Episode> {
        return repository.getEpisodes().toDomainModel()
    }
}
