package ru.kheynov.santa.core.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class AvatarDTO(
    val id: Int,
    val image: String,
)