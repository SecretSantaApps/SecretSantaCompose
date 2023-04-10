package ru.kheynov.santa.core.domain.repository

import ru.kheynov.santa.core.domain.entity.AvatarDTO

interface GetAvatarsRepository {
    suspend fun getAvailableAvatars(): List<AvatarDTO>
}