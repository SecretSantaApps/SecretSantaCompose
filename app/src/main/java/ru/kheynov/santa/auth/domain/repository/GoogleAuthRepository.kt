package ru.kheynov.santa.auth.domain.repository

import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.util.Resource

interface GoogleAuthRepository {
    suspend fun registerViaGoogle(credentials: AuthDTO.SignInViaGoogle): Resource<TokenPair>
    suspend fun loginViaGoogle(credentials: AuthDTO.LoginViaGoogle): Resource<TokenPair>
}