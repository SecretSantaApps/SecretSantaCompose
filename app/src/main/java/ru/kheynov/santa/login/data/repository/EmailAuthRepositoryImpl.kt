package ru.kheynov.santa.login.data.repository

import ru.kheynov.santa.login.data.mappers.toLoginDTO
import ru.kheynov.santa.login.data.mappers.toRegisterDTO
import ru.kheynov.santa.login.data.mappers.toTokenPair
import ru.kheynov.santa.login.data.remote.EmailAuthAPI
import retrofit2.HttpException
import ru.kheynov.santa.core.entity.TokenPair
import ru.kheynov.santa.core.util.UserAlreadyExistsException
import ru.kheynov.santa.login.domain.model.AuthDTO
import ru.kheynov.santa.login.domain.repository.EmailAuthRepository
import ru.kheynov.santa.login.domain.util.Resource

class EmailAuthRepositoryImpl(
    private val api: EmailAuthAPI,
) : EmailAuthRepository {
    override suspend fun registerViaEmail(credentials: AuthDTO.SignInViaEmail): Resource<TokenPair> {
        return try {
            val response = api.registerViaEmail(credentials.toRegisterDTO()).toTokenPair()
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Failure(
                when (e.code()) {
                    409 -> UserAlreadyExistsException()
                    else -> e
                }
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
    
    
    override suspend fun loginViaEmail(credentials: AuthDTO.LoginViaEmail): Resource<TokenPair> {
        return try {
            val response = api.loginViaEmail(credentials.toLoginDTO()).toTokenPair()
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Failure(
                when (e.code()) {
                    409 -> UserAlreadyExistsException()
                    else -> e
                }
            )
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
    
}