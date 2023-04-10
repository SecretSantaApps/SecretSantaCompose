package ru.kheynov.santa.auth.domain.useCases

import javax.inject.Inject
import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.repository.EmailAuthRepository
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.core.util.Resource

class LoginViaEmailUseCase @Inject constructor(
    private val repository: EmailAuthRepository,
    private val tokenStorage: TokenStorage,
) {
    suspend operator fun invoke(credentials: AuthDTO.LoginViaEmail): Resource<Unit> =
        when (
            val result = repository.loginViaEmail(credentials)
        ) {
            is Resource.Success -> {
                tokenStorage.saveTokenPair(result.result)
                Resource.Success(Unit)
            }
            is Resource.Failure -> {
                result
            }
        }
}