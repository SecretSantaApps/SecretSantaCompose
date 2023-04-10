package ru.kheynov.santa.core.data.repository

import javax.inject.Inject
import retrofit2.HttpException
import ru.kheynov.santa.auth.data.mappers.toTokenPair
import ru.kheynov.santa.core.data.remote.RefreshTokenAPI
import ru.kheynov.santa.core.domain.entity.RefreshTokenDTO
import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.domain.repository.RefreshTokenRepository
import ru.kheynov.santa.core.util.ForbiddenException
import ru.kheynov.santa.core.util.InvalidRefreshTokenException
import ru.kheynov.santa.core.util.RefreshTokenExpiredException
import ru.kheynov.santa.core.util.Resource

class RefreshTokenRepositoryImpl @Inject constructor(
    private val api: RefreshTokenAPI,
) : RefreshTokenRepository {
    override suspend fun refreshToken(refreshToken: String): Resource<TokenPair> {
        return try {
            val response = api.refreshTokenPair(RefreshTokenDTO(refreshToken)).toTokenPair()
            Resource.Success(response)
        } catch (e: Exception) {
            if (e is HttpException) {
                Resource.Failure(
                    e.response()?.errorBody()?.string().toString()
                        .let { message ->
                            when (e.code()) {
                                400 -> {
                                    if (message.contains("Invalid")) {
                                        InvalidRefreshTokenException()
                                    } else if (message.contains("expired")) {
                                        RefreshTokenExpiredException()
                                    } else {
                                        e
                                    }
                                }
                                403 -> ForbiddenException()
                                else -> e
                            }
                        },
                )
            } else {
                Resource.Failure(e)
            }
        }
    }
}