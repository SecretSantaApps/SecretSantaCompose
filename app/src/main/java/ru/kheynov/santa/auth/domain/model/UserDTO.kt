package ru.kheynov.santa.auth.domain.model

sealed interface UserDTO {
    class SignUpInfo(
        val name: String,
    )
}