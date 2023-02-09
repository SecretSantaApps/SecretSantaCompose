package ru.kheynov.santa.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.kheynov.santa.core.data.preferences.DefaultClientIdStorage
import ru.kheynov.santa.core.data.remote.ClientIdInterceptor
import ru.kheynov.santa.core.data.token_storage.DefaultTokenStorage
import ru.kheynov.santa.core.domain.preferences.ClientIdStorage
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.login.data.remote.EmailAuthAPI
import ru.kheynov.santa.login.data.repository.EmailAuthRepositoryImpl
import ru.kheynov.santa.login.domain.repository.EmailAuthRepository
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    
    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application,
    ): SharedPreferences =
        app.getSharedPreferences("client-id", Context.MODE_PRIVATE)
    
    @Provides
    @Singleton
    fun provideClientIdStorage(sharedPreferences: SharedPreferences): ClientIdStorage =
        DefaultClientIdStorage(sharedPreferences)
    
    @Provides
    @Singleton
    fun provideOkHttpClient(clientIdStorage: ClientIdStorage): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            ClientIdInterceptor(clientIdStorage)
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideTokenStorage(app: Application): TokenStorage {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        
        val sharedPreferences = EncryptedSharedPreferences.create(
            "TokenPair",
            masterKeyAlias,
            app,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return DefaultTokenStorage(sharedPreferences)
    }
    
    @Provides
    @Singleton
    fun provideEmailAuthApi(httpClient: OkHttpClient, json: Json): EmailAuthAPI {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(EmailAuthAPI.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
            .create(EmailAuthAPI::class.java)
    }
    
    @Provides
    @Singleton
    fun provideEmailAuthRepository(api: EmailAuthAPI): EmailAuthRepository =
        EmailAuthRepositoryImpl(api)
    
}