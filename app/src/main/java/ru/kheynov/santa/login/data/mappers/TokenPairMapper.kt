package ru.kheynov.santa.login.data.mappers

import remote.dto.TokenPairDTO
import ru.kheynov.santa.core.entity.TokenPair

internal fun TokenPairDTO.toTokenPair(): TokenPair {
    return TokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken.refreshToken,
        refreshTokenExpiration = refreshToken.expiration
    )
}