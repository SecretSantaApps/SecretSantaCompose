package ru.kheynov.santa.core.domain.repository

import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.util.Resource

interface RefreshTokenRepository {
    suspend fun refreshToken(refreshToken: String): Resource<TokenPair>
}