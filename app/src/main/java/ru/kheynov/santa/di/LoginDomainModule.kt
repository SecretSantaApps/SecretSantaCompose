package ru.kheynov.santa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.kheynov.santa.core.domain.preferences.TokenStorage
import ru.kheynov.santa.login.domain.repository.EmailAuthRepository
import ru.kheynov.santa.login.domain.use_case.LoginViaEmailUseCase

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {
    @Provides
    @ViewModelScoped
    fun provideEmailLoginUseCase(
        emailAuthRepository: EmailAuthRepository,
        tokenStorage: TokenStorage,
    ): LoginViaEmailUseCase {
        return LoginViaEmailUseCase(emailAuthRepository, tokenStorage)
    }
}