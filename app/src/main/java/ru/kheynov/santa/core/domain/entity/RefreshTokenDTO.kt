package ru.kheynov.santa.core.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDTO(
    val refreshToken: String,
)