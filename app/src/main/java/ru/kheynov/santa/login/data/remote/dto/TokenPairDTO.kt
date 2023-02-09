package remote.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TokenPairDTO(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: RefreshToken,
)

@kotlinx.serialization.Serializable
data class RefreshToken(
    @SerialName("token") val refreshToken: String,
    val expiration: Long,
)