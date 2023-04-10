package ru.kheynov.santa.core.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kheynov.santa.auth.data.remote.dto.TokenPairDTO
import ru.kheynov.santa.core.domain.entity.RefreshTokenDTO

interface RefreshTokenAPI {
    @POST("auth/refresh")
    suspend fun refreshTokenPair(@Body body: RefreshTokenDTO): TokenPairDTO
}