package ru.kheynov.santa.login.domain.repository

import ru.kheynov.santa.core.entity.TokenPair
import ru.kheynov.santa.login.domain.model.AuthDTO
import ru.kheynov.santa.login.domain.util.Resource

interface GoogleAuthRepository {
    suspend fun registerViaGoogle(credentials: AuthDTO.SignInViaGoogle): Resource<TokenPair>
    suspend fun loginViaGoogle(credentials: AuthDTO.LoginViaGoogle): Resource<TokenPair>
}