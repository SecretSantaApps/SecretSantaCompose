package ru.kheynov.santa.core.domain.useCases

import javax.inject.Inject
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.core.domain.repository.RefreshTokenRepository
import ru.kheynov.santa.core.util.Resource

class RefreshTokenUseCase @Inject constructor(
    private val repository: RefreshTokenRepository,
    private val tokenStorage: TokenStorage,
) {
    suspend operator fun invoke(refreshToken: String): Resource<Unit> {
        return when (val result = repository.refreshToken(refreshToken)) {
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