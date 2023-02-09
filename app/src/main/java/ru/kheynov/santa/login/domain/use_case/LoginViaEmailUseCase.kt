package ru.kheynov.santa.login.domain.use_case

import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.login.domain.model.AuthDTO
import ru.kheynov.santa.login.domain.repository.EmailAuthRepository
import ru.kheynov.santa.login.domain.util.Resource

class LoginViaEmailUseCase(
    private val repository: EmailAuthRepository,
    private val tokenStorage: TokenStorage,
) {
    
    suspend operator fun invoke(credentials: AuthDTO.LoginViaEmail): Resource<Unit> {
        return when (val result = repository.loginViaEmail(credentials)) {
            is Resource.Success -> {
                tokenStorage.saveTokenPair(result.result)
                Resource.Success(Unit)
            }
            is Resource.Failure -> {
                result
            }
        }
    }
}