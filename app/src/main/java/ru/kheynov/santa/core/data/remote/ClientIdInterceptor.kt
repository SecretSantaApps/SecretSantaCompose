package ru.kheynov.santa.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import ru.kheynov.santa.core.domain.preferences.ClientIdStorage

class ClientIdInterceptor(
    private val clientIdStorage: ClientIdStorage,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val id = clientIdStorage.getClientId()
        val newRequest =
            request.newBuilder()
                .addHeader("Client-Id", id)
                .build()
        return chain.proceed(newRequest)
    }

    fun aboba() {
        val list = listOf('1', '2')
        list.map { it.uppercase() }
    }
}