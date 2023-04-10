package ru.kheynov.santa.auth.domain.repository

import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.util.Resource

interface EmailAuthRepository {
    suspend fun signUpViaEmail(credentials: AuthDTO.SignUpViaEmail): Resource<TokenPair>
    suspend fun loginViaEmail(credentials: AuthDTO.LoginViaEmail): Resource<TokenPair>
}