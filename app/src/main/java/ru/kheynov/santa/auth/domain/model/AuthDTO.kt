package ru.kheynov.santa.auth.domain.model

sealed interface AuthDTO {
    @kotlinx.serialization.Serializable
    class SignUpViaEmail(
        val username: String,
        val email: String,
        val password: String,
        val address: String?,
        val avatar: Int = 1,
    ) : AuthDTO

    @kotlinx.serialization.Serializable
    class LoginViaEmail(
        val email: String,
        val password: String,
    ) : AuthDTO

    @kotlinx.serialization.Serializable
    class SignInViaGoogle(
        val token: String,
//        val userInfo: UserDTO.SignUpInfo,
    ) : AuthDTO

    @kotlinx.serialization.Serializable
    class LoginViaGoogle(
        val token: String,
    ) : AuthDTO
}