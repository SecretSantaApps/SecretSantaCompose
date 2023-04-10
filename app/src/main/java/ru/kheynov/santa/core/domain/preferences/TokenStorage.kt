package ru.kheynov.santa.core.domain.preferences

import ru.kheynov.santa.core.domain.entity.TokenPair

interface TokenStorage {
    suspend fun saveTokenPair(tokenPair: TokenPair)
    suspend fun loadTokenPair(): TokenPair
}