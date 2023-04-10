package ru.kheynov.santa.auth.data.repository

import retrofit2.HttpException
import ru.kheynov.santa.auth.data.mappers.toTokenPair
import ru.kheynov.santa.auth.data.remote.EmailAuthAPI
import ru.kheynov.santa.auth.domain.model.AuthDTO
import ru.kheynov.santa.auth.domain.repository.EmailAuthRepository
import ru.kheynov.santa.core.domain.entity.TokenPair
import ru.kheynov.santa.core.util.Resource
import ru.kheynov.santa.core.util.UserAlreadyExistsException

class EmailAuthRepositoryImpl(
    private val api: EmailAuthAPI,
) : EmailAuthRepository {
    override suspend fun signUpViaEmail(credentials: AuthDTO.SignUpViaEmail): Resource<TokenPair> {
        return try {
            val response = api.registerViaEmail(credentials).toTokenPair()
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Failure(
                when (e.code()) {
                    409 -> UserAlreadyExistsException()
                    else -> e
                },
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun loginViaEmail(credentials: AuthDTO.LoginViaEmail): Resource<TokenPair> {
        return try {
            val response = api.loginViaEmail(credentials).toTokenPair()
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Failure(
                when (e.code()) {
                    409 -> UserAlreadyExistsException()
                    else -> e
                },
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}