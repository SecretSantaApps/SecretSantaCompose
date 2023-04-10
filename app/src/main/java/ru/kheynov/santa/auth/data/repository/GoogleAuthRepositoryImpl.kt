package ru.kheynov.santa.auth.data.repository

import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.repository.GoogleAuthRepository
import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.util.Resource

class GoogleAuthRepositoryImpl : GoogleAuthRepository {
    override suspend fun registerViaGoogle(credentials: AuthDTO.SignInViaGoogle):
        Resource<TokenPair> {
        TODO("Not yet implemented")
    }

    override suspend fun loginViaGoogle(credentials: AuthDTO.LoginViaGoogle):
        Resource<TokenPair> {
        TODO("Not yet implemented")
    }
}