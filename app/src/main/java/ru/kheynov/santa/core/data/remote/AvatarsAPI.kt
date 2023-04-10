package ru.kheynov.santa.core.data.remote

import retrofit2.http.GET
import ru.kheynov.santa.core.domain.entity.AvatarDTO

interface AvatarsAPI {
    @GET("assets")
    suspend fun getAvailableAvatars(): List<AvatarDTO>
}