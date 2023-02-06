package ru.kheynov.santa.core.domain.preferences

interface ClientIdStorage {
    fun getClientId(): String
    
    companion object {
        const val ID_KEY = "client-id"
    }
}