package ru.kheynov.santa.core.entity

@kotlinx.serialization.Serializable
data class TokenPair(
    val accessToken: String,
    val refreshToken: String,
    val refreshTokenExpiration: Long,
)