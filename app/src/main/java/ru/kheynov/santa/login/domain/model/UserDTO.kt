package ru.kheynov.santa.login.domain.model

sealed interface UserDTO {
    class RegisterInformation(
        val name: String,
    )
}