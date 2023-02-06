package ru.kheynov.santa.core.data.preferences

import android.content.SharedPreferences
import ru.kheynov.santa.core.domain.preferences.ClientIdStorage
import java.util.*

class DefaultClientIdStorage(
    private val pref: SharedPreferences,
) : ClientIdStorage {
    private var id: String = pref.getString(ClientIdStorage.ID_KEY, null) ?: run {
        val id = UUID.randomUUID().toString().subSequence(0, 6).toString()
        pref.edit().putString(ClientIdStorage.ID_KEY, id).apply()
        id
    }
    
    override fun getClientId(): String {
        return id
    }
}