package ru.kheynov.santa.core.data.token_storage

import android.content.SharedPreferences
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.core.entity.TokenPair

class DefaultTokenStorage(
    private val sharedPreferences: SharedPreferences,
) : TokenStorage {
    override suspend fun saveTokenPair(tokenPair: TokenPair) {
        sharedPreferences.edit().apply {
            putString(KEY_ACCESS_TOKEN, tokenPair.accessToken)
            putString(KEY_REFRESH_TOKEN, tokenPair.refreshToken)
            putLong(KEY_EXPIRATION, tokenPair.refreshTokenExpiration)
        }.apply()
    }
    
    override suspend fun loadTokenPair(): TokenPair {
        return TokenPair(
            accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, "").toString(),
            refreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, "").toString(),
            refreshTokenExpiration = sharedPreferences.getLong(KEY_EXPIRATION, 0)
        )
    }
    
    companion object {
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_REFRESH_TOKEN = "refresh_token"
        const val KEY_EXPIRATION = "refresh_token_expiration"
    }
}