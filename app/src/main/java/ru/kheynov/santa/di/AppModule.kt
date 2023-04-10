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
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.kheynov.santa.auth.data.remote.EmailAuthAPI
import ru.kheynov.santa.auth.data.repository.EmailAuthRepositoryImpl
import ru.kheynov.santa.auth.domain.repository.EmailAuthRepository
import ru.kheynov.santa.core.data.preferences.DefaultClientIdStorage
import ru.kheynov.santa.core.data.remote.AvatarsAPI
import ru.kheynov.santa.core.data.remote.ClientIdInterceptor
import ru.kheynov.santa.core.data.remote.RefreshTokenAPI
import ru.kheynov.santa.core.data.repository.GetAvatarsRepositoryImpl
import ru.kheynov.santa.core.data.repository.RefreshTokenRepositoryImpl
import ru.kheynov.santa.core.data.tokenStorage.DefaultTokenStorage
import ru.kheynov.santa.core.domain.preferences.ClientIdStorage
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.core.domain.repository.GetAvatarsRepository
import ru.kheynov.santa.core.domain.repository.RefreshTokenRepository

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

    private const val BASE_URL = "https://santa.s.kheynov.ru/api/v1/"

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
            ClientIdInterceptor(clientIdStorage),
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
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
        return DefaultTokenStorage(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideEmailAuthApi(retrofit: Retrofit): EmailAuthAPI =
        retrofit.create(EmailAuthAPI::class.java)

    @Provides
    @Singleton
    fun provideAvatarsApi(retrofit: Retrofit): AvatarsAPI =
        retrofit.create(AvatarsAPI::class.java)

    @Provides
    @Singleton
    fun provideRefreshTokenApi(retrofit: Retrofit): RefreshTokenAPI =
        retrofit.create(RefreshTokenAPI::class.java)

    @Provides
    @Singleton
    fun provideEmailAuthRepository(api: EmailAuthAPI): EmailAuthRepository =
        EmailAuthRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideRefreshTokenRepository(api: RefreshTokenAPI): RefreshTokenRepository =
        RefreshTokenRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideAvatarsRepository(api: AvatarsAPI): GetAvatarsRepository =
        GetAvatarsRepositoryImpl(api)
}