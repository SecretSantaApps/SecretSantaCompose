package ru.kheynov.santa.login.domain.repository

import ru.kheynov.santa.core.entity.TokenPair
import ru.kheynov.santa.login.domain.model.AuthDTO
import ru.kheynov.santa.login.domain.util.Resource

interface EmailAuthRepository {
    suspend fun registerViaEmail(credentials: AuthDTO.SignInViaEmail): Resource<TokenPair>
    suspend fun loginViaEmail(credentials: AuthDTO.LoginViaEmail): Resource<TokenPair>
}