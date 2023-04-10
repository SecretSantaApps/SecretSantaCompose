package ru.kheynov.santa.auth.data.mappers

import ru.kheynov.santa.auth.data.remote.dto.TokenPairDTO
import ru.kheynov.santa.core.domain.entity.TokenPair

internal fun TokenPairDTO.toTokenPair(): TokenPair {
    return TokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken.refreshToken,
        refreshTokenExpiration = refreshToken.expiration,
    )
}