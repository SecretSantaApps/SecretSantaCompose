package ru.kheynov.santa.core.data.repository

import ru.kheynov.santa.core.data.remote.AvatarsAPI
import ru.kheynov.santa.core.domain.entity.AvatarDTO
import ru.kheynov.santa.core.domain.repository.GetAvatarsRepository

class GetAvatarsRepositoryImpl(
    private val api: AvatarsAPI,
) : GetAvatarsRepository {
    override suspend fun getAvailableAvatars(): List<AvatarDTO> {
        return api.getAvailableAvatars()
    }
}