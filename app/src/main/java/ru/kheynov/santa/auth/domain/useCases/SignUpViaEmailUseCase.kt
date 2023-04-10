package ru.kheynov.santa.auth.domain.useCases

import javax.inject.Inject
import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.repository.EmailAuthRepository
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.core.util.Resource

class SignUpViaEmailUseCase @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val repository: EmailAuthRepository,
) {
    suspend operator fun invoke(credentials: AuthDTO.SignUpViaEmail): Resource<Unit> =
        when (
            val
            result = repository.signUpViaEmail(credentials)
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