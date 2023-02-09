package ru.kheynov.santa.login.data.remote

import remote.dto.LoginViaEmailDTO
import remote.dto.RegisterViaEmailDTO
import remote.dto.TokenPairDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailAuthAPI {
    @POST("email/register")
    suspend fun registerViaEmail(@Body body: RegisterViaEmailDTO): TokenPairDTO
    
    @POST("email/login")
    suspend fun loginViaEmail(@Body body: LoginViaEmailDTO): TokenPairDTO
    
    companion object {
        const val BASE_URL = "https://santa.s.kheynov.ru/api/v1/auth/"
    }
}