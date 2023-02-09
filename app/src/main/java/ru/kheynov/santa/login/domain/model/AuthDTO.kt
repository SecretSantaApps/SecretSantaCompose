package ru.kheynov.santa.login.domain.model

sealed interface AuthDTO {
    class SignInViaEmail(
        val email: String,
        val password: String,
        val userInfo: UserDTO.RegisterInformation,
    ) : AuthDTO
    
    class LoginViaEmail(
        val email: String,
        val password: String,
    ) : AuthDTO
    
    class SignInViaGoogle(
        val token: String,
        val userInfo: UserDTO.RegisterInformation,
    ) : AuthDTO
    
    class LoginViaGoogle(
        val token: String,
    ) : AuthDTO
}