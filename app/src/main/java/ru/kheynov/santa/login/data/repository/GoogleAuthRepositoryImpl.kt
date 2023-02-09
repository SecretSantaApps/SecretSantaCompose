package ru.kheynov.santa.login.data.repository

import ru.kheynov.santa.core.entity.TokenPair
import ru.kheynov.santa.login.domain.model.AuthDTO
import ru.kheynov.santa.login.domain.repository.GoogleAuthRepository
import ru.kheynov.santa.login.domain.util.Resource

class GoogleAuthRepositoryImpl : GoogleAuthRepository {
    override suspend fun registerViaGoogle(credentials: AuthDTO.SignInViaGoogle): Resource<TokenPair> {
        TODO("Not yet implemented")
    }
    
    override suspend fun loginViaGoogle(credentials: AuthDTO.LoginViaGoogle): Resource<TokenPair> {
        TODO("Not yet implemented")
    }
}