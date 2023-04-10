package ru.kheynov.santa.auth.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kheynov.santa.auth.data.remote.dto.TokenPairDTO
import ru.kheynov.santa.auth.domain.model.AuthDTO

interface EmailAuthAPI {
    @POST("auth/email/register")
    suspend fun registerViaEmail(@Body body: AuthDTO.SignUpViaEmail): TokenPairDTO

    @POST("auth/email/login")
    suspend fun loginViaEmail(@Body body: AuthDTO.LoginViaEmail): TokenPairDTO
}