package ru.kheynov.santa

import android.app.Application
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp
import ru.kheynov.santa.core.domain.preferences.ClientIdStorage
import javax.inject.Inject

private const val ONESIGNAL_APP_ID = "e281e33f-9662-49c7-ad62-71bad853fc3a"

@HiltAndroidApp
class SantaApp : Application() {
    
    @Inject
    lateinit var clientIdStorage: ClientIdStorage
    
    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.promptForPushNotifications()
        val clientId = clientIdStorage.getClientId()
        OneSignal.setExternalUserId(clientId)
    }
}